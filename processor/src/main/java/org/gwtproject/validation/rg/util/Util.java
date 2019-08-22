/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.validation.rg.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.validation.GroupSequence;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.gwtproject.validation.client.GwtValidation;
import org.gwtproject.validation.context.AptContext;

/**
 * Static utilities for the validation rebind package.
 */
public final class Util {

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();
    /**
     * The size of a {@link #threadLocalBuf}, which should be large enough for
     * efficient data transfer but small enough to fit easily into the L2 cache of
     * most modern processors.
     */
    private static final int THREAD_LOCAL_BUF_SIZE = 16 * 1024;
    /**
     * Stores reusable thread local buffers for efficient data transfer.
     */
    private static final ThreadLocal<byte[]> threadLocalBuf = new ThreadLocal<byte[]>();
    public static String DEFAULT_ENCODING = "UTF-8";


    private Util() {
    }

    /**
     * Creates a Predicate that returns false if source contains an associated
     * class that is a super type of the class associated with the tested T.
     * @param <T> the type to test
     * @param source the set of <T> to look for class matches.
     * @param toClass Function from T to Class
     * @return newly create predicate.
     */
    public static <T> Predicate<T> createMostSpecificMatchPredicate(AptContext context,
                                                                    final Iterable<T> source, final Function<T, TypeElement> toClass) {
        return input -> {
            TypeElement inputClass = toClass.apply(input);
            for (TypeElement match : Iterables.transform(source, toClass)) {
                System.out.println("? " + match + " " + inputClass);
                System.out.println(context.types.isAssignable(match.asType(), inputClass.asType()));
                if (!inputClass.equals(match) && context.types.isAssignable(match.asType(), inputClass.asType())) {
                    return false;
                }
            }
            return true;
        };
    }

    /**
     * Selects first only the classes that are assignable from the target, and
     * then returns the most specific matching classes.
     * @param target the Class to match
     * @param availableClasses classes to search
     * @return Set of only the most specific classes that match the target.
     */
    public static Set<TypeElement> findBestMatches(AptContext context, TypeElement target,
                                                   Set<TypeElement> availableClasses) {
        Set<TypeElement> matches = new HashSet<>();
        if (availableClasses.contains(target)) {
            return ImmutableSet.of(target);
        } else {
            for (TypeElement clazz : availableClasses) {
                System.out.println("findBestMatches isAssignable " + target.asType() + " " + clazz.asType() + " " + context.types.isAssignable(target.asType(), clazz.asType()));
                throw new UnsupportedOperationException("findBestMatches");
/*        context.types.isAssignable(target.asType(), clazz.asType());
        if (clazz.isAssignableFrom(target)) {
          matches.add(clazz);
        }*/
            }
        }
        Predicate<TypeElement> moreSpecificClassPredicate = createMostSpecificMatchPredicate(context,
                                                                                             matches, Functions.identity());
        return Sets.filter(matches, moreSpecificClassPredicate);
    }

    public static VariableElement getDeclaredField(TypeElement elem, String propertyName) {
        for (Element enclosedElement : elem.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.FIELD) {
                Set<Modifier> modifiers = enclosedElement.getModifiers();
                StringBuilder sb = new StringBuilder();
                if (modifiers.contains(Modifier.PRIVATE)) {
                    sb.append("private ");
                } else if (modifiers.contains(Modifier.PROTECTED)) {
                    sb.append("protected ");
                } else if (modifiers.contains(Modifier.PUBLIC)) {
                    sb.append("public ");
                }
                if (modifiers.contains(Modifier.STATIC)) {
                    sb.append("static ");
                }
                if (modifiers.contains(Modifier.FINAL)) {
                    sb.append("final ");
                }
                sb.append(enclosedElement.asType()).append(" ").append(enclosedElement.getSimpleName());
                System.out.println(sb);
            }
        }
        return null;
    }

    public static List<ExecutableElement> getMethods(Elements elements, TypeElement element) {
        return elements.getAllMembers(element)
                .stream().filter(f -> f.getKind() == ElementKind.METHOD)
                .map(m -> MoreElements.asExecutable(m))
                .collect(Collectors.toList());
    }

    public static ExecutableElement getMethod(Elements elements, TypeElement element, String propertyName) {
        return getMethods(elements, element)
                .stream()
                .filter(f -> f.getSimpleName().toString().equals(propertyName))
                .findFirst().orElseThrow(() -> new Error("Unable to find a methid " + propertyName + " in " + element.getQualifiedName()));
    }

    public static List<TypeElement> getGroupSequence(GroupSequence annotation) {
        try {
            annotation.value();
        } catch (javax.lang.model.type.MirroredTypesException mte) {
            return mte.getTypeMirrors().stream().map(m -> MoreTypes.asTypeElement(m)).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    public static List<TypeElement> getValues(GwtValidation annotation) {
        try {
            annotation.value();
        } catch (javax.lang.model.type.MirroredTypesException mte) {
            return mte.getTypeMirrors().stream().map(m -> MoreTypes.asTypeElement(m)).collect(Collectors.toList());
        }
        return null;
    }

    public static List<TypeElement> getGroups(GwtValidation annotation) {
        try {
            annotation.groups();
        } catch (javax.lang.model.type.MirroredTypesException mte) {
            return mte.getTypeMirrors().stream().map(m -> MoreTypes.asTypeElement(m)).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Returns a Immutable List sorted with the most specific associated class
     * first. Each element is guaranteed to not be assignable to any element that
     * appears before it in the list.
     */
    public static <T> ImmutableList<T> sortMostSpecificFirst(
            AptContext context, Iterable<T> classes,
            Function<T, TypeElement> toClass) {
        List<T> working = Lists.newArrayList();
        // strip duplicates
        for (T t : classes) {
            if (!working.contains(t)) {
                working.add(t);
            }
        }
        List<T> sorted = Lists.newArrayList();
        Predicate<T> mostSpecific = createMostSpecificMatchPredicate(context, working,
                                                                     toClass);
        boolean changed = false;
        do {
            changed = false;
            for (Iterator<T> iterator = working.iterator(); iterator.hasNext(); ) {
                T t = iterator.next();
                if (mostSpecific.apply(t)) {
                    sorted.add(t);
                    iterator.remove();
                    changed = true;
                }
            }
        } while (changed);
        if (!working.isEmpty()) {
            throw new IllegalStateException(
                    "Unable to find a element that does not have a more specific element in the set "
                            + working);
        }
        return ImmutableList.copyOf(sorted);
    }

    public static boolean isInterface(TypeMirror typeMirror) {
        return TypeKind.DECLARED.equals(typeMirror.getKind()) && ((DeclaredType) typeMirror).asElement().getKind().isInterface();
    }

    /**
     * Test if the given {@link TypeMirror} represents a class or not.
     */
    public static boolean isClass(TypeMirror typeMirror) {
        return TypeKind.DECLARED.equals(typeMirror.getKind()) && ((DeclaredType) typeMirror).asElement().getKind().isClass();
    }

    public static String getSimpleClassName(TypeElement element) {
        StringBuffer stringBuffer = new StringBuffer();
        if (isInterface(element.getEnclosingElement().asType()) || isClass(element.getEnclosingElement().asType())) {
            stringBuffer.append(element.getEnclosingElement().getSimpleName().toString());
            stringBuffer.append("_");
        }
        stringBuffer.append(element.getSimpleName().toString());
        return stringBuffer.toString();
    }

    /**
     * Returns the field of the class
     */
    public static VariableElement findFieldInType(TypeElement type, String name) {
        for (VariableElement field: ElementFilter.fieldsIn(type.getEnclosedElements())) {
            if (field.getSimpleName().toString().equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * see: typetools/checker-framework
     */
    public static Set<VariableElement> findFieldsInType(TypeElement type, Collection<String> names) {
        Set<VariableElement> results = new HashSet<>();
        for (VariableElement field: ElementFilter.fieldsIn(type.getEnclosedElements())) {
            if (names.contains(field.getSimpleName().toString())) {
                results.add(field);
            }
        }
        return results;
    }

    /**
     * see: typetools/checker-framework
     */
    public static List<VariableElement> getAllFieldsIn(Elements elements, TypeElement type) {
        List<VariableElement> fields = new ArrayList<>();
        fields.addAll(ElementFilter.fieldsIn(type.getEnclosedElements()));
        List<TypeElement> alltypes = getSuperTypes(elements, type);
        for (TypeElement atype : alltypes) {
            fields.addAll(ElementFilter.fieldsIn(atype.getEnclosedElements()));
        }
        return Collections.unmodifiableList(fields);
    }

    /**
     * see: typetools/checker-framework
     * Return all methods declared in the given type or any superclass/interface.
     * Note that no constructors will be returned.
     * TODO: should this use javax.lang.model.util.Elements.getAllMembers(TypeElement)
     * instead of our own getSuperTypes?
     */
    public static List<ExecutableElement> getAllMethodsIn(Elements elements, TypeElement type) {
        List<ExecutableElement> meths = new ArrayList<>();
        meths.addAll(ElementFilter.methodsIn(type.getEnclosedElements()));

        List<TypeElement> alltypes = getSuperTypes(elements, type);
        for (TypeElement atype : alltypes) {
            meths.addAll(ElementFilter.methodsIn(atype.getEnclosedElements()));
        }
        return Collections.unmodifiableList(meths);
    }

    /**
     * see: typetools/checker-framework
     * Determine all type elements for the classes and interfaces referenced
     * in the extends/implements clauses of the given type element.
     * TODO: can we learn from the implementation of
     * com.sun.tools.javac.model.JavacElements.getAllMembers(TypeElement)?
     */
    public static List<TypeElement> getSuperTypes(Elements elements, TypeElement type) {

        List<TypeElement> superelems = new ArrayList<>();
        if (type == null) {
            return superelems;
        }

        // Set up a stack containing type, which is our starting point.
        Deque<TypeElement> stack = new ArrayDeque<>();
        stack.push(type);

        while (!stack.isEmpty()) {
            TypeElement current = stack.pop();

            // For each direct supertype of the current type element, if it
            // hasn't already been visited, push it onto the stack and
            // add it to our superelems set.
            TypeMirror supertypecls = current.getSuperclass();
            if (supertypecls.getKind() != TypeKind.NONE) {
                TypeElement supercls = (TypeElement) ((DeclaredType)supertypecls).asElement();
                if (!superelems.contains(supercls)) {
                    stack.push(supercls);
                    superelems.add(supercls);
                }
            }
            for (TypeMirror supertypeitf : current.getInterfaces()) {
                TypeElement superitf = (TypeElement) ((DeclaredType)supertypeitf).asElement();
                if (!superelems.contains(superitf)) {
                    stack.push(superitf);
                    superelems.add(superitf);
                }
            }
        }

        // Include java.lang.Object as implicit superclass for all classes and interfaces.
        TypeElement jlobject = elements.getTypeElement("java.lang.Object");
        if (!superelems.contains(jlobject)) {
            superelems.add(jlobject);
        }

        return Collections.unmodifiableList(superelems);
    }

    /**
     * Returns a String representing the character content of the bytes; the bytes
     * must be encoded using the compiler's default encoding.
     */
    public static String toString(byte[] bytes) {
        return toString(bytes, DEFAULT_ENCODING);
    }

    /**
     * Creates a string from the bytes using the specified character set name.
     *
     * @param bytes       bytes to convert
     * @param charsetName the name of the character set to use
     * @return String for the given bytes and character set or <code>null</code>
     * if the character set is not supported
     */
    private static String toString(byte[] bytes, String charsetName) {
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            // Ignored.
        }

        return null;
    }

    /**
     * Release a buffer previously returned from {@link #takeThreadLocalBuf()}.
     * The released buffer may then be reused.
     */
    public static void releaseThreadLocalBuf(byte[] buf) {
        assert buf.length == THREAD_LOCAL_BUF_SIZE;
        threadLocalBuf.set(buf);
    }

    /**
     * Get a large byte buffer local to this thread. Currently this is set to a
     * 16k buffer, which is small enough to fit into the L2 cache on modern
     * processors. The contents of the returned buffer are undefined. Calling
     * {@link #releaseThreadLocalBuf(byte[])} on the returned buffer allows
     * subsequent callers to reuse the buffer later, avoiding unncessary
     * allocations and GC.
     */
    public static byte[] takeThreadLocalBuf() {
        byte[] buf = threadLocalBuf.get();
        if (buf == null) {
            buf = new byte[THREAD_LOCAL_BUF_SIZE];
        } else {
            threadLocalBuf.set(null);
        }
        return buf;
    }

    /**
     * Computes the MD5 hash for the specified byte array.
     *
     * @return a big fat string encoding of the MD5 for the content, suitably
     * formatted for use as a file name
     */
    public static String computeStrongName(byte[] content) {
        return computeStrongName(new byte[][]{content});
    }

    /**
     * Returns a byte-array representing the default encoding for a String.
     */
    public static byte[] getBytes(String s) {
        try {
            return s.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(
                    "The JVM does not support the compiler's default encoding.", e);
        }
    }

    /**
     * Computes the MD5 hash of the specified byte arrays.
     *
     * @return a big fat string encoding of the MD5 for the content, suitably
     * formatted for use as a file name
     */
    public static String computeStrongName(byte[][] contents) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing MD5", e);
        }

        /*
         * Include the lengths of the contents components in the hash, so that the
         * hashed sequence of bytes is in a one-to-one correspondence with the
         * possible arguments to this method.
         */
        ByteBuffer b = ByteBuffer.allocate((contents.length + 1) * 4);
        b.putInt(contents.length);
        for (int i = 0; i < contents.length; i++) {
            b.putInt(contents[i].length);
        }
        b.flip();
        md5.update(b);

        // Now hash the actual contents of the arrays
        for (int i = 0; i < contents.length; i++) {
            md5.update(contents[i]);
        }
        return printHexBinary(md5.digest()).toUpperCase();
    }

    public static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        byte[] var3 = data;
        int var4 = data.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            r.append(hexCode[b >> 4 & 15]);
            r.append(hexCode[b & 15]);
        }

        return r.toString();
    }
}

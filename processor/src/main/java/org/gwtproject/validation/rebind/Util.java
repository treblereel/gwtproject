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
package org.gwtproject.validation.rebind;

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
import javax.lang.model.util.Elements;
import javax.validation.GroupSequence;

/**
 * Static utilities for the validation rebind package.
 */
final class Util {

  /**
   * Creates a Predicate that returns false if source contains an associated
   * class that is a super type of the class associated with the tested T.
   *
   * @param <T> the type to test
   * @param source the set of <T> to look for class matches.
   * @param toClass Function from T to Class
   * @return newly create predicate.
   */
  static <T> Predicate<T> createMostSpecificMatchPredicate(AptContext context,
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
   *
   * @param target the Class to match
   * @param availableClasses classes to search
   * @return Set of only the most specific classes that match the target.
   */
  static Set<TypeElement> findBestMatches(AptContext context, TypeElement target,
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


  static VariableElement getDeclaredField(TypeElement elem, String propertyName) {
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
        if (modifiers.contains(Modifier.STATIC))
          sb.append("static ");
        if (modifiers.contains(Modifier.FINAL))
          sb.append("final ");
        sb.append(enclosedElement.asType()).append(" ").append(enclosedElement.getSimpleName());
        System.out.println(sb);
      }
    }
    return null;
  }

  static List<ExecutableElement> getMethods(Elements elements, TypeElement element) {
    return elements.getAllMembers(element)
            .stream().filter(f -> f.getKind() == ElementKind.METHOD)
            .map(m -> MoreElements.asExecutable(m))
            .collect(Collectors.toList());
  }

  static ExecutableElement getMethod(Elements elements, TypeElement element, String propertyName) {
    return getMethods(elements, element)
            .stream()
            .filter( f -> f.getSimpleName().toString().equals(propertyName))
            .findFirst().orElseThrow(() -> new Error("Unable to find a methid " + propertyName + " in " + element.getQualifiedName()));
  }

  static List<TypeElement> getGroupSequence(GroupSequence annotation) {
    try {
      annotation.value();
    } catch (javax.lang.model.type.MirroredTypesException mte) {
      return mte.getTypeMirrors().stream().map(m -> MoreTypes.asTypeElement(m)).collect(Collectors.toList());
    }
    return null;
  }

  static List<TypeElement> getValues(GwtValidation annotation) {
    try {
      annotation.value();
    } catch (javax.lang.model.type.MirroredTypesException mte) {
      return mte.getTypeMirrors().stream().map(m -> MoreTypes.asTypeElement(m)).collect(Collectors.toList());
    }
    return null;
  }

  static List<TypeElement> getGroups(GwtValidation annotation) {
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
  static <T> ImmutableList<T> sortMostSpecificFirst(
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
      for (Iterator<T> iterator = working.iterator(); iterator.hasNext();) {
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
    return TypeKind.DECLARED.equals(typeMirror.getKind() ) && ( (DeclaredType) typeMirror ).asElement().getKind().isInterface();
  }

  /**
   * Test if the given {@link TypeMirror} represents a class or not.
   */
  public static boolean isClass(TypeMirror typeMirror) {
    return TypeKind.DECLARED.equals( typeMirror.getKind() ) && ( (DeclaredType) typeMirror ).asElement().getKind().isClass();
  }

  public static String getSimpleClassName(TypeElement element) {
    StringBuffer stringBuffer = new StringBuffer();
    if(isInterface(element.getEnclosingElement().asType()) || isClass(element.getEnclosingElement().asType())) {
      stringBuffer.append(element.getEnclosingElement().getSimpleName().toString());
      stringBuffer.append("_");
    }
    stringBuffer.append(element.getSimpleName().toString());
    return stringBuffer.toString();
  }

  private Util() {
  }
}

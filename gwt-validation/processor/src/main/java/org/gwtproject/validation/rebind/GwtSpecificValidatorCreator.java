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
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.Path.Node;
import javax.validation.Payload;
import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.groups.Default;
import org.gwtproject.validation.client.impl.AbstractGwtSpecificValidator;
import org.gwtproject.validation.client.impl.ConstraintDescriptorImpl;
import org.gwtproject.validation.client.impl.ConstraintOrigin;
import org.gwtproject.validation.client.impl.Group;
import org.gwtproject.validation.client.impl.GroupChain;
import org.gwtproject.validation.client.impl.GroupChainGenerator;
import org.gwtproject.validation.client.impl.GwtBeanDescriptor;
import org.gwtproject.validation.client.impl.GwtBeanDescriptorImpl;
import org.gwtproject.validation.client.impl.GwtValidationContext;
import org.gwtproject.validation.client.impl.PathImpl;
import org.gwtproject.validation.client.impl.PropertyDescriptorImpl;
import org.gwtproject.validation.client.impl.metadata.BeanMetadata;
import org.gwtproject.validation.client.impl.metadata.ValidationGroupsMetadata;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.ext.Generator;
import org.gwtproject.validation.rebind.beaninfo.BeanInfo;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;
import org.gwtproject.validation.rebind.beaninfo.ConstraintValidator;
import org.gwtproject.validation.rebind.beaninfo.PropertyDescriptor;
import org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl.ClassWrapper;
import org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl.DefaultValueHolder;
import org.gwtproject.validation.rebind.ext.GeneratorContext;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;
import org.gwtproject.validation.rg.util.SourceWriter;
import org.gwtproject.validation.rg.util.Util;

/**
 * Creates a {@link org.gwtproject.validation.client.impl.GwtSpecificValidator}.
 *
 * <p>This class is not thread safe.
 */
public final class GwtSpecificValidatorCreator extends AbstractCreator {

  static final TypeElement[] NO_ARGS = new TypeElement[] {};
  private static final String DEFAULT_VIOLATION_VAR = "violations";
  private static Function<Object, String> TO_LITERAL = GwtSpecificValidatorCreator::asLiteral;
  private final BeanHelper beanHelper;
  private final Set<BeanHelper> beansToValidate = Sets.newHashSet();
  private final TypeElement beanType;
  private final Set<VariableElement> fieldsToWrap = Sets.newHashSet();
  private final Set<TypeElement> validGroups;
  private final Map<ConstraintDescriptor, Boolean> validConstraintsMap = Maps.newHashMap();
  private Set<ExecutableElement> gettersToWrap = Sets.newHashSet();
  private TypeElement object;

  public GwtSpecificValidatorCreator(
      TypeElement beanType,
      BeanHelper beanHelper,
      TreeLogger logger,
      GeneratorContext context,
      BeanHelperCache cache,
      List<TypeElement> validGroupsFromAnnotation) {
    super(context, logger, beanHelper.getPackage(), beanHelper.getValidatorName(), cache);
    this.beanType = beanType;
    this.beanHelper = beanHelper;

    Set<TypeElement> tempValidGroups = Sets.newHashSet(validGroupsFromAnnotation);
    tempValidGroups.add(
        context.getAptContext().elements.getTypeElement(Default.class.getCanonicalName()));

    this.validGroups = Collections.unmodifiableSet(tempValidGroups);

    this.object = context.getAptContext().elements.getTypeElement(Object.class.getCanonicalName());
  }

  public static String asGetter(PropertyDescriptor p, AptContext context) {
    return asGetter(p.getPropertyName(), p.getElementClass(), context);
  }

  public static String asGetter(String name, TypeMirror type, AptContext context) {
    if (type.getKind().equals(TypeKind.BOOLEAN)
        || (type.getKind().equals(TypeKind.DECLARED)
            && type.toString().equals(Boolean.class.getCanonicalName()))) {
      String candidate = "is" + capitalizeFirstLetter(name);

      if (Util.getMethods(context.elements, MoreTypes.asTypeElement(type)).stream()
              .filter(
                  executableElement ->
                      executableElement.getSimpleName().toString().equals(candidate))
              .count()
          > 0) {
        return candidate;
      }
    }
    return "get" + capitalizeFirstLetter(name);
  }

  /**
   * Returns the literal value of an object that is suitable for inclusion in Java Source code.
   *
   * <p>Supports all types that {@link Annotation} value can have.
   *
   * @throws IllegalArgumentException if the type of the object does not have a java literal form.
   */
  public static String asLiteral(Object value) {

    Class<?> clazz = value.getClass();
    if (clazz.isArray()) {
      if (clazz.getComponentType().equals(ClassWrapper.class)) {
        ClassWrapper[] classes = (ClassWrapper[]) value;
        StringBuffer sb = new StringBuffer();
        sb.append("new java.lang.Class[] {");
        sb.append(
            Arrays.stream(classes)
                .map(m -> m.getClazz() + ".class")
                .collect(Collectors.joining(",")));
        sb.append("}");
        return sb.toString();
      }

      StringBuilder sb = new StringBuilder();
      Object[] array = (Object[]) value;

      sb.append("new " + clazz.getComponentType().getCanonicalName() + "[] ");
      sb.append("{");
      boolean first = true;
      for (Object object : array) {
        if (first) {
          first = false;
        } else {
          sb.append(",");
        }
        sb.append(asLiteral(object));
      }
      sb.append("}");
      return sb.toString();
    }

    if (value instanceof Boolean) {
      return ((Boolean) value).booleanValue() + "";
    } else if (value instanceof Byte) {
      return ((Byte) value).byteValue() + "";
    } else if (value instanceof Character) {
      return ((Character) value).charValue() + "";
    } else if (value instanceof Double) {
      return ((Double) value).doubleValue() + "";
    } else if (value instanceof Enum) {
      return value.getClass().getCanonicalName() + "." + ((Enum<?>) value).name();
    } else if (value instanceof Float) {
      return ((Float) value).floatValue() + "";
    } else if (value instanceof Integer) {
      return ((Integer) value).intValue() + "";
    } else if (value instanceof Long) {
      return ((Long) value).longValue() + "L";
    } else if (value instanceof String) {
      return '"' + Generator.escape((String) value) + '"';
    } else if (value
        .getClass()
        .getCanonicalName()
        .equals("com.sun.tools.javac.code.Attribute.Enum")) {
      return value.toString();
    } else if (value instanceof Class<?>) {
      if (((Class<?>) value).isArray()
          && ((Class<?>) value).getComponentType().equals(ClassWrapper.class)) {
        return "java.lang.Class[]";
      }
      return ((Class<?>) value).getCanonicalName();
    } else {
      // TODO(nchalko) handle Annotation types
      throw new IllegalArgumentException(
          value.getClass() + " can not be represented as a Java Literal.");
    }
  }

  public static String capitalizeFirstLetter(String propertyName) {
    if (propertyName == null) {
      return null;
    }
    if (propertyName.length() == 0) {
      return "";
    }
    String cap = propertyName.substring(0, 1).toUpperCase(Locale.ROOT);
    if (propertyName.length() > 1) {
      cap += propertyName.substring(1);
    }
    return cap;
  }

  public static boolean isIterableOrMap(AptContext context, TypeMirror elementClass) {
    TypeMirror iterable =
        context.elements.getTypeElement(Iterable.class.getCanonicalName()).asType();
    TypeMirror map = context.elements.getTypeElement(Map.class.getCanonicalName()).asType();
    if (elementClass.getKind().equals(TypeKind.ARRAY)
        || context.types.isAssignable(context.types.erasure(elementClass), iterable)
        || context.types.isAssignable(context.types.erasure(elementClass), map)) {
      return true;
    }
    return false;
  }

  /**
   * Finds the type that a constraint validator will check.
   *
   * <p>This type comes from the first parameter of the isValid() method on the constraint
   * validator. However, this is a bit tricky because ConstraintValidator has a parameterized type.
   * When using Java reflection, we will see multiple isValid() methods, including one that checks
   * java.lang.Object.
   *
   * <p>Strategy: for now, assume there are at most two isValid() methods. If there are two, assume
   * one of them has a type that is assignable from the other. (Most likely, one of them will be
   * java.lang.Object.)
   *
   * @throws IllegalStateException if there isn't any isValid() method or there are more than two.
   */
  static TypeMirror getTypeOfConstraintValidator(AptContext context, String constraintClassName) {
    TypeElement type = context.elements.getTypeElement(constraintClassName);
    int candidateCount = 0;
    TypeMirror result = null;

    for (ExecutableElement method :
        type.getEnclosedElements().stream()
            .filter(elm -> (elm).getKind().equals(ElementKind.METHOD))
            .map(MoreElements::asExecutable)
            .collect(Collectors.toSet())) {
      if (method.getSimpleName().toString().equals("isValid")
          && method.getParameters().size() == 2) {
        VariableElement param = method.getParameters().get(0);
        if (result == null) {
          result = param.asType();
        }
        candidateCount++;
      }
    }

    if (candidateCount == 0) {
      throw new IllegalStateException("ConstraintValidators must have a isValid method");
    } else if (candidateCount > 2) {
      throw new IllegalStateException(
          "ConstraintValidators must have no more than two isValid methods");
    }
    return result;
  }

  // Visible for testing
  static ImmutableSet<String> getValidatorForType(
      AptContext context, TypeMirror type, List<String> constraintValidatorClasses) {
    Map<TypeMirror, String> map = Maps.newHashMap();
    for (String constraintClass : constraintValidatorClasses) {
      TypeMirror aType = getTypeOfConstraintValidator(context, constraintClass);
      if (context.types.isAssignable(type, aType)) {
        map.put(aType, constraintClass);
      }
    }
    // TODO(nchalko) implement per spec
    // Handle Arrays and Generics

    final Set<TypeMirror> best = Util.findBestMatches(context, type, map.keySet());

    Predicate<TypeMirror> inBest = key -> best.contains(key);

    return ImmutableSet.copyOf(Maps.filterKeys(map, inBest).values());
  }

  /**
   * Gets the best {@link ConstraintValidator}.
   *
   * <p>The ConstraintValidator chosen to validate a declared type {@code targetType} is the one
   * where the type supported by the ConstraintValidator is a supertype of {@code targetType} and
   * where there is no other ConstraintValidator whose supported type is a supertype of {@code type}
   * and not a supertype of the chosen ConstraintValidator supported type.
   *
   * @param constraint the constraint to find ConstraintValidators for.
   * @param targetType The type to find a ConstraintValidator for.
   * @return ConstraintValidator
   * @throws UnexpectedTypeException if there is not exactly one maximally specific constraint
   *     validator for targetType.
   */
  private static String getValidatorForType(
      AptContext context, ConstraintDescriptor constraint, TypeMirror targetType) {
    List<String> constraintValidatorClasses = constraint.getConstraintValidatorClasses();
    if (constraintValidatorClasses.isEmpty()) {
      throw new UnexpectedTypeException(
          "No ConstraintValidator found for  " + constraint.getAnnotation());
    }
    ImmutableSet<String> best =
        getValidatorForType(context, targetType, constraintValidatorClasses);
    if (best.isEmpty()) {
      throw new UnexpectedTypeException(
          "No " + constraint.getAnnotation() + " ConstraintValidator for type " + targetType);
    }
    if (best.size() > 1) {
      throw new UnexpectedTypeException(
          "More than one maximally specific "
              + constraint.getAnnotation()
              + " ConstraintValidator for type "
              + targetType
              + ", found "
              + Ordering.usingToString().sortedCopy(best));
    }
    return Iterables.get(best, 0);
  }

  @Override
  protected void compose(ClassSourceFileComposerFactory composerFactory) {
    addImports(
        composerFactory,
        Annotation.class,
        ConstraintViolation.class,
        ValidationGroupsMetadata.class,
        Group.class,
        GroupChain.class,
        PathImpl.class,
        Node.class,
        GroupChainGenerator.class,
        GwtBeanDescriptor.class,
        BeanMetadata.class,
        GwtValidationContext.class,
        ArrayList.class,
        HashSet.class,
        IllegalArgumentException.class,
        Set.class,
        Collection.class,
        Iterator.class,
        List.class,
        ValidationException.class);
    composerFactory.setSuperclass(
        AbstractGwtSpecificValidator.class.getCanonicalName()
            + "<"
            + beanType.getQualifiedName()
            + ">");
    composerFactory.addImplementedInterface(
        beanHelper.getPackage() + "." + beanHelper.getValidatorName());
  }

  @Override
  protected void writeClassBody(SourceWriter sw) throws UnableToCompleteException {
    writeFields(sw);
    sw.println();
    writeValidateClassGroups(sw);
    sw.println();
    writeExpandDefaultAndValidateClassGroups(sw);
    sw.println();
    writeExpandDefaultAndValidatePropertyGroups(sw);
    sw.println();
    writeExpandDefaultAndValidateValueGroups(sw);
    sw.println();
    writeValidatePropertyGroups(sw);
    sw.println();
    writeValidateValueGroups(sw);
    sw.println();
    writeGetBeanMetadata(sw);
    sw.println();
    writeGetDescriptor(sw);
    sw.println();
    writePropertyValidators(sw);
    sw.println();
    writeValidateAllNonInheritedProperties(sw);
    sw.println();

    // Write the wrappers after we know which are needed
    writeWrappers(sw);
    sw.println();
  }

  protected void writeUnsafeNativeLongIfNeeded(SourceWriter sw, TypeMirror jType) {
    if (jType.getKind().isPrimitive() && (jType).getKind().equals(TypeKind.LONG)) {
      sw.print("@");
      sw.println("com.google.gwt.core.client.UnsafeNativeLong");
    }
  }

  private boolean areConstraintDescriptorGroupsValid(ConstraintDescriptor constraintDescriptor) {
    if (validConstraintsMap.containsKey(constraintDescriptor)) {
      return validConstraintsMap.get(constraintDescriptor);
    } else {
      boolean areValid = checkGroups(constraintDescriptor.getGroups());
      // cache result
      validConstraintsMap.put(constraintDescriptor, areValid);
      return areValid;
    }
  }

  private <T> T[] asArray(Collection<?> collection, T[] array) {
    if (collection == null) {
      return null;
    }
    return collection.toArray(array);
  }

  private boolean checkGroups(Object[] groups) {
    ClassWrapper[] _groups = (ClassWrapper[]) groups;
    for (ClassWrapper group : _groups) {
      if (validGroups.contains(context.getAptContext().elements.getTypeElement(group.getClazz()))) {
        return true;
      }
    }
    return false;
  }

  private String constraintDescriptorVar(String name, int count) {
    String s = name + "_c" + count;
    return s;
  }

  private AnnotationMirror getAnnotation(
      PropertyDescriptor p, boolean useField, String expectedAnnotationClass) {
    AnnotationMirror annotation = null;
    if (useField) {
      VariableElement field = beanHelper.getField(p.getPropertyName());
      if (field.getEnclosingElement().equals(beanType)) {
        annotation =
            field.getAnnotationMirrors().stream()
                .filter(
                    ano ->
                        ano.getAnnotationType()
                            .asElement()
                            .toString()
                            .equals(expectedAnnotationClass))
                .findFirst()
                .orElse(null);
      }
    } else {
      ExecutableElement method =
          beanHelper.findMethod(asGetter(p, context.getAptContext()), Collections.emptyList());
      if (method.getEnclosingElement().equals(beanType)) {
        throw new Error(
            "Unable to process method " + method + " in " + method.getEnclosingElement());
      }
    }
    // maybe inherited
    if (context.getAptContext().isSupported(expectedAnnotationClass)) {
      annotation =
          p.getConstraintDescriptors().stream()
              .filter(
                  desc ->
                      MoreElements.asType(desc.getAnnotation().getAnnotationType().asElement())
                          .getQualifiedName()
                          .toString()
                          .equals(expectedAnnotationClass))
              .map(ConstraintDescriptor::getAnnotation)
              .findFirst()
              .orElse(null);
    }

    return annotation;
  }

  private List<? extends AnnotationMirror> getAnnotations(PropertyDescriptor p, boolean useField) {
    if (useField) {
      VariableElement field = beanHelper.getField(p.getPropertyName());
      return field.getAnnotationMirrors();
    } else {
      ExecutableElement method =
          beanHelper.findMethod(p.getPropertyName(), Collections.emptyList());
      return method.getAnnotationMirrors();
    }
  }

  private String getQualifiedSourceNonPrimitiveType(TypeMirror elementType) {
    if (elementType.getKind().isPrimitive()) {
      return context
          .getAptContext()
          .types
          .boxedClass(MoreTypes.asPrimitiveType(elementType))
          .getQualifiedName()
          .toString();
    }
    return elementType.toString();
  }

  private boolean hasMatchingAnnotation(
      String expectedAnnotation, List<? extends AnnotationMirror> annotations)
      throws UnableToCompleteException {
    // See spec section 2.2. Applying multiple constraints of the same type
    for (AnnotationMirror annotation : annotations) {
      // annotations not annotated by @Constraint
      if (annotation.getAnnotationType().getAnnotation(Constraint.class) == null) {
        try {
          // value element has a return type of an array of constraint
          // annotations
          ExecutableElement valueMethod =
              MoreElements.asExecutable(
                  annotation.getAnnotationType().asElement().getEnclosedElements().stream()
                      .filter(elm -> elm.getKind().equals(ElementKind.METHOD))
                      .filter(elm -> elm.getSimpleName().toString().equals("value"))
                      .findFirst()
                      .orElseThrow(NoSuchMethodException::new));
          TypeMirror annotationTypeMirror =
              context
                  .getAptContext()
                  .elements
                  .getTypeElement(Annotation.class.getCanonicalName())
                  .asType();
          TypeMirror valueType = valueMethod.getReturnType();
          if (valueType.getKind().equals(TypeKind.ARRAY)
              && context.getAptContext().types.isAssignable(annotationTypeMirror, valueType)) {
            for (AnnotationMirror mirror : valueType.getAnnotationMirrors()) {
              if (mirror.toString().equals(expectedAnnotation)) {
                return true;
              }
            }
          }
        } catch (NoSuchMethodException ignore) {
          // Expected Case.
        } catch (Exception e) {
          throw error(logger, e);
        }
      }
    }
    return false;
  }

  private boolean hasMatchingAnnotation(ConstraintDescriptor constraint) {
    String expectedAnnotation =
        MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
            .getQualifiedName()
            .toString();
    for (AnnotationMirror ano : beanHelper.getClazz().getAnnotationMirrors()) {
      if (ano.toString().equals(expectedAnnotation)) {
        return true;
      }
    }

    return false;
  }

  private boolean hasMatchingAnnotation(
      PropertyDescriptor p, boolean useField, ConstraintDescriptor constraint)
      throws UnableToCompleteException {

    String expectedAnnotation =
        MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
            .getQualifiedName()
            .toString();
    AnnotationMirror annotationMirror = getAnnotation(p, useField, expectedAnnotation);
    if (annotationMirror == null) {
      return false;
    }
    if (expectedAnnotation.equals(annotationMirror.getAnnotationType().toString())) {
      return true;
    }
    return hasMatchingAnnotation(expectedAnnotation, getAnnotations(p, useField));
  }

  private boolean hasValid(PropertyDescriptor p, boolean useField) {
    return getAnnotation(p, useField, Valid.class.getCanonicalName()) != null;
  }

  private boolean isPropertyConstrained(BeanHelper helper, PropertyDescriptor p) {
    Set<PropertyDescriptor> propertyDescriptors =
        helper.getBeanDescriptor().getConstrainedProperties();
    Predicate<PropertyDescriptor> nameMatches = newPropertyNameMatches(p);
    return Iterables.any(propertyDescriptors, nameMatches);
  }

  private boolean isPropertyConstrained(PropertyDescriptor p, boolean useField) {
    // cascaded counts as constrained
    // we must know if the @Valid annotation is on a field or a getter
    if (useField && beanHelper.getField(p.getPropertyName()).getAnnotation(Valid.class) != null) {
      return true;
    } else if (!useField
        && beanHelper
                .findMethod(asGetter(p, context.getAptContext()), Collections.emptyList())
                .getAnnotation(Valid.class)
            != null) {
      return true;
    }
    // for non-cascaded properties
    for (ConstraintDescriptor constraint : p.getConstraintDescriptors()) {
      org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl constraintHibernate =
          (org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl) constraint;
      if (constraintHibernate.getSource().getKind()
          == (useField ? ElementKind.FIELD : ElementKind.METHOD)) {
        return true;
      }
    }
    return false;
  }

  private Predicate<PropertyDescriptor> newPropertyNameMatches(final PropertyDescriptor p) {
    return input -> input.getPropertyName().equals(p.getPropertyName());
  }

  private String toWrapperName(VariableElement field) {
    return "_" + field.getSimpleName().toString();
  }

  private String toWrapperName(ExecutableElement method) {
    return "_" + method.getSimpleName().toString();
  }

  private String validateMethodFieldName(PropertyDescriptor p) {
    return "validateProperty_" + p.getPropertyName();
  }

  private String validateMethodGetterName(PropertyDescriptor p) {
    return "validateProperty_get" + p.getPropertyName();
  }

  private void writeBeanDescriptor(SourceWriter sw) {
    BeanInfo beanDescriptor = beanHelper.getBeanDescriptor();

    // private final GwtBeanDescriptor <MyBean> beanDescriptor =
    sw.print("private final ");
    sw.print(GwtBeanDescriptor.class.getCanonicalName());
    sw.print("<" + beanHelper.getTypeCanonicalName() + ">");
    sw.println(" beanDescriptor = ");
    sw.indent();
    sw.indent();

    // GwtBeanDescriptorImpl.builder(Order.class)
    sw.print(GwtBeanDescriptorImpl.class.getCanonicalName());
    sw.println(".builder(" + beanHelper.getTypeCanonicalName() + ".class)");
    sw.indent();
    sw.indent();
    sw.println(".setConstrained(true)");

    int count = 0;

    for (ConstraintDescriptor constraint : beanDescriptor.getConstraintDescriptors()) {
      if (areConstraintDescriptorGroupsValid(constraint)) {
        // .add(c0)
        sw.println(".add(" + constraintDescriptorVar("this", count) + ")");
        count++;
      }
    }

    // .put("myProperty", myProperty_pd)
    for (PropertyDescriptor p : beanDescriptor.getConstrainedProperties()) {
      sw.print(".put(\"");
      sw.print(p.getPropertyName());
      sw.print("\", ");
      sw.print(p.getPropertyName());
      sw.println("_pd)");
    }

    // .setBeanMetadata(beanMetadata)
    sw.println(".setBeanMetadata(beanMetadata)");

    // .build();
    sw.println(".build();");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writeBeanMetadata(SourceWriter sw) throws UnableToCompleteException {
    // private final BeanMetadata beanMetadata =
    sw.println("private final BeanMetadata beanMetadata =");
    sw.indent();
    sw.indent();

    // new BeanMetadata(
    sw.println("new " + BeanMetadata.class.getSimpleName() + "(");
    sw.indent();
    sw.indent();

    // <<bean class>>, <<default group seq class 1>>, <<default group seq class 2>>, ...
    TypeElement beanClazz = beanHelper.getClazz();
    sw.print(beanClazz.getQualifiedName().toString() + ".class");
    GroupSequence groupSeqAnnotation = beanClazz.getAnnotation(GroupSequence.class);
    List<TypeElement> groupSequence = new ArrayList<>();
    if (groupSeqAnnotation == null) {
      groupSequence.add(beanClazz);
    } else {
      groupSequence.addAll(Util.getGroupSequence(groupSeqAnnotation));
    }
    boolean groupSequenceContainsDefault = false;
    for (TypeElement group : groupSequence) {
      sw.println(",");
      if (group.getQualifiedName().equals(beanClazz.getQualifiedName())) {
        sw.print(Default.class.getCanonicalName() + ".class");
        groupSequenceContainsDefault = true;
      } else if (group.getQualifiedName().toString().equals(Default.class.getCanonicalName())) {
        throw error(logger, "'Default.class' cannot appear in default group sequence list.");
      } else {
        sw.print(asLiteral(group));
      }
    }
    if (!groupSequenceContainsDefault) {
      throw error(
          logger,
          beanClazz.getQualifiedName()
              + " must be part of the redefined default group "
              + "sequence.");
    }

    sw.println(");");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writeClassLevelConstraintsValidation(SourceWriter sw, String groupsVarName) {
    // all class level constraints
    int count = 0;
    TypeElement clazz = beanHelper.getClazz();
    for (ConstraintDescriptor constraint :
        beanHelper.getBeanDescriptor().getConstraintDescriptors()) {
      if (areConstraintDescriptorGroupsValid(constraint)) {
        if (hasMatchingAnnotation(constraint)) {

          if (!constraint.getConstraintValidatorClasses().isEmpty()) {
            Class<? extends ConstraintValidator<? extends Annotation, ?>> validatorClass = null;
            // validate(context, violations, null, object,
            sw.print("validate(context, violations, null, object, ");

            // new MyValidtor(),
            sw.print("new ");
            sw.print(validatorClass.getCanonicalName());
            sw.print("(), "); // TODO(nchalko) use ConstraintValidatorFactory

            // this.aConstraintDescriptor, groups);
            sw.print(constraintDescriptorVar("this", count));
            sw.print(", ");
            sw.print(groupsVarName);
            sw.println(");");
          } else if (constraint.getComposingConstraints().isEmpty()) {
            // TODO(nchalko) What does the spec say to do here.
            logger.log(
                TreeLogger.WARN, "No ConstraintValidator of " + constraint + " for type " + clazz);
          }
          // TODO(nchalko) handle constraint.isReportAsSingleViolation() and
          // hasComposingConstraints
        }
        count++;
      }
    }
  }

  private void writeConstraintDescriptor(
      SourceWriter sw,
      ConstraintDescriptor constraint,
      String elementType,
      ConstraintOrigin origin,
      String constraintDescripotorVar)
      throws UnableToCompleteException {
    String annotationType =
        MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
            .getQualifiedName()
            .toString();

    // First list all composing constraints
    int count = 0;
    for (ConstraintDescriptor composingConstraint : constraint.getComposingConstraints()) {
      writeConstraintDescriptor(
          sw, composingConstraint, elementType, origin, constraintDescripotorVar + "_" + count++);
    }

    // private final ConstraintDescriptorImpl<MyAnnotation> constraintDescriptor = ;
    sw.print("private final ");
    sw.print(ConstraintDescriptorImpl.class.getCanonicalName());
    sw.print("<");

    sw.print(annotationType);
    sw.print(">");

    sw.println(" " + constraintDescripotorVar + "  = ");
    sw.indent();
    sw.indent();

    // ConstraintDescriptorImpl.<MyConstraint> builder()
    sw.print(ConstraintDescriptorImpl.class.getCanonicalName());
    sw.print(".<");

    sw.print(annotationType);
    sw.println("> builder()");
    sw.indent();
    sw.indent();

    // .setAnnotation(new MyAnnotation )
    sw.println(".setAnnotation( ");
    sw.indent();
    sw.indent();
    writeNewAnnotation(sw, constraint);
    sw.println(")");
    sw.outdent();
    sw.outdent();

    // .setAttributes(builder()
    sw.println(".setAttributes(attributeBuilder()");
    sw.indent();

    for (Map.Entry<String, DefaultValueHolder> entry : constraint.getAttributes().entrySet()) {
      // .put(key, value)
      sw.print(".put(");
      String key = entry.getKey();
      sw.print(asLiteral(key));
      sw.print(", ");
      DefaultValueHolder value = entry.getValue();
      // Add the Default group if it is not already present
      if ("groups".equals(key) && value.type.equals("java.lang.Class<?>[]")) {
        sw.print("new Class[]{javax.validation.groups.Default.class}");
      } else {
        sw.print(value.value.toString());
      }
      sw.println(")");
    }

    // .build())
    sw.println(".build())");
    sw.outdent();

    // .setConstraintValidatorClasses(classes )
    sw.print(".setConstraintValidatorClasses(");
    sw.print("new java.lang.Class[] {");

    String constraints =
        context.getAptContext()
            .getValidators(
                MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
                    .getQualifiedName()
                    .toString())
            .stream()
            .map(m -> m + ".class")
            .collect(Collectors.joining(","));
    sw.print(
        context.getAptContext()
            .getValidators(
                MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
                    .getQualifiedName()
                    .toString())
            .stream()
            .map(m -> m + ".class")
            .collect(Collectors.joining(",")));
    sw.println("})");

    int ccCount = constraint.getComposingConstraints().size();
    for (int i = 0; i < ccCount; i++) {
      // .addComposingConstraint(cX_X)
      sw.print(".addComposingConstraint(");
      sw.print(constraintDescripotorVar + "_" + i);
      sw.println(")");
    }

    // .getGroups(groups)
    sw.print(".setGroups(");
    Object[] groups = constraint.getGroups();
    sw.print(asLiteral(groups));
    sw.println(")");

    // .setPayload(payload)
    sw.print(".setPayload(");
    Set<Class<? extends Payload>> payload = constraint.getPayload();

    sw.print(asLiteral(asArray(payload, new Class[0])));
    sw.println(")");

    // .setReportAsSingleViolation(boolean )
    sw.print(".setReportAsSingleViolation(");
    sw.print(Boolean.valueOf(constraint.isReportAsSingleViolation()).toString());
    sw.println(")");

    // .setElementType(elementType)
    sw.print(".setElementType(");
    sw.print("java.lang.annotation.ElementType.FIELD");
    sw.println(")");

    // .setDefinedOn(origin)
    sw.print(".setDefinedOn(");
    sw.print(asLiteral(origin));
    sw.println(")");

    // .build();
    sw.println(".build();");
    sw.outdent();
    sw.outdent();

    sw.outdent();
    sw.outdent();
    sw.println();
  }

  private void writeExpandDefaultAndValidate(SourceWriter sw, Stage stage)
      throws UnableToCompleteException {
    TypeElement clazz = beanHelper.getClazz();

    // ArrayList<Class<?>> justGroups = new ArrayList<Class<?>>();
    sw.println("ArrayList<Class<?>> justGroups = new ArrayList<Class<?>>();");

    // for (Group g : groups) {
    sw.println("for (Group g : groups) {");
    sw.indent();
    //  if (!g.isDefaultGroup() || !getBeanMetadata().defaultGroupSequenceIsRedefined()) {
    sw.println(
        "if (!g.isDefaultGroup() || !getBeanMetadata().defaultGroupSequenceIsRedefined()) {");
    sw.indent();
    // justGroups.add(g.getGroup());
    sw.println("justGroups.add(g.getGroup());");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");

    // Class<?>[] justGroupsArray = justGroups.toArray(new Class<?>[justGroups.size()]);
    sw.println("Class<?>[] justGroupsArray = justGroups.toArray(new Class<?>[justGroups.size()]);");

    switch (stage) {
      case OBJECT:
        // validateAllNonInheritedProperties(context, object, violations, justGroupsArray);
        sw.println(
            "validateAllNonInheritedProperties(context, object, violations, "
                + "justGroupsArray);");
        writeClassLevelConstraintsValidation(sw, "justGroupsArray");
        break;
      case PROPERTY:
        // validatePropertyGroups(context, object, propertyName, violations, justGroupsArray);
        sw.println(
            "validatePropertyGroups(context, object, propertyName, violations, "
                + "justGroupsArray);");
        break;
      case VALUE:
        // validateValueGroups(context, beanType, propertyName, value, violations,
        //     justGroupsArray);
        sw.println(
            "validateValueGroups(context, beanType, propertyName, value, violations, "
                + "justGroupsArray);");
        break;
      default:
        throw new IllegalStateException();
    }

    // if (getBeanMetadata().defaultGroupSequenceIsRedefined()) {
    sw.println("if (getBeanMetadata().defaultGroupSequenceIsRedefined()) {");
    sw.indent();
    // for (Class<?> g : beanMetadata.getDefaultGroupSequence()) {
    sw.println("for (Class<?> g : beanMetadata.getDefaultGroupSequence()) {");
    sw.indent();
    // int numberOfViolations = violations.size();
    sw.println("int numberOfViolations = violations.size();");

    switch (stage) {
      case OBJECT:
        // validateAllNonInheritedProperties(context, object, violations, g);
        sw.println("validateAllNonInheritedProperties(context, object, violations, g);");
        writeClassLevelConstraintsValidation(sw, "g");
        // validate super classes and super interfaces
        writeValidateInheritance(sw, clazz, Stage.OBJECT, null, false, "g");
        break;
      case PROPERTY:
        // validatePropertyGroups(context, object, propertyName, violations, g);
        sw.println("validatePropertyGroups(context, object, propertyName, violations, g);");
        break;
      case VALUE:
        // validateValueGroups(context, beanType, propertyName, value, violations, g);
        sw.println("validateValueGroups(context, beanType, propertyName, value, violations, g);");
        break;
      default:
        throw new IllegalStateException();
    }

    // if (violations.size() > numberOfViolations) {
    sw.println("if (violations.size() > numberOfViolations) {");
    sw.indent();
    // break;
    sw.println("break;");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");
    sw.outdent();
    // }
    sw.println("}");
    if (stage == Stage.OBJECT) {
      // else {
      sw.println("else {");
      sw.indent();

      // validate super classes and super interfaces
      writeValidateInheritance(sw, clazz, Stage.OBJECT, null, true, "groups");

      // }
      sw.outdent();
      sw.println("}");
    }
  }

  private void writeExpandDefaultAndValidateClassGroups(SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidateClassGroups(
    sw.println("public <T> void expandDefaultAndValidateClassGroups(");

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    writeExpandDefaultAndValidate(sw, Stage.OBJECT);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeExpandDefaultAndValidatePropertyGroups(SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidatePropertyGroups(
    sw.println("public <T> void expandDefaultAndValidatePropertyGroups(");

    // GwtValidationContext<T> context, BeanType object, String propertyName,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(beanHelper.getTypeCanonicalName() + " object,");
    sw.println("String propertyName,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    writeExpandDefaultAndValidate(sw, Stage.PROPERTY);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeExpandDefaultAndValidateValueGroups(SourceWriter sw)
      throws UnableToCompleteException {
    // public <T> void expandDefaultAndValidateValueGroups(
    sw.println("public <T> void expandDefaultAndValidateValueGroups(");

    // GwtValidationContext<T> context, Class<Author> beanType, String propertyName,
    // Object value, Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println("Class<" + beanHelper.getTypeCanonicalName() + "> beanType,");
    sw.println("String propertyName,");
    sw.println("Object value,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Group... groups) {");
    sw.outdent();

    writeExpandDefaultAndValidate(sw, Stage.VALUE);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeFields(SourceWriter sw) throws UnableToCompleteException {
    // private static final java.util.List<String> ALL_PROPERTY_NAMES =
    sw.println("private static final java.util.List<String> ALL_PROPERTY_NAMES = ");
    sw.indent();
    sw.indent();

    // Collections.<String>unmodifiableList(
    sw.println("java.util.Collections.<String>unmodifiableList(");
    sw.indent();
    sw.indent();

    // java.util.Arrays.<String>asList(
    sw.print("java.util.Arrays.<String>asList(");
    sw.print(
        beanHelper.getPropertyDescriptors().stream()
            .map(PropertyDescriptor::getPropertyName)
            .map(TO_LITERAL)
            .collect(Collectors.joining(",")));
    sw.println("));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // Write the metadata for the bean
    writeBeanMetadata(sw);
    sw.println();

    // Create a variable for each constraint of each property
    for (PropertyDescriptor p : beanHelper.getBeanDescriptor().getConstrainedProperties()) {
      int count = 0;

      for (ConstraintDescriptor constraint : p.getConstraintDescriptors()) {
        if (areConstraintDescriptorGroupsValid(constraint)
            && !constraint
                .getAnnotation()
                .toString()
                .equals(javax.validation.Valid.class.getCanonicalName())) {
          writeConstraintDescriptor(
              sw,
              constraint,
              MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
                  .getQualifiedName()
                  .toString(),
              ConstraintOrigin.DEFINED_LOCALLY,
              constraintDescriptorVar(p.getPropertyName(), count++));
        }
      }
      writePropertyDescriptor(sw, p);
      if (p.isCascaded()) {
        beansToValidate.add(
            isIterableOrMap(context.getAptContext(), p.getElementClass())
                ? createBeanHelper(beanHelper.getAssociationType(p, true))
                : createBeanHelper(MoreTypes.asTypeElement(p.getElementClass())));
      }
    }
    // Now write the BeanDescriptor after we already have the
    // PropertyDescriptors and class constraints
    writeBeanDescriptor(sw);
    sw.println();
  }

  private void writeFieldWrapperMethod(SourceWriter sw, VariableElement field) {
    writeUnsafeNativeLongIfNeeded(sw, field.asType());

    // private native fieldType _fieldName(com.example.Bean object) /*-{
    sw.print("private ");

    sw.print(field.asType().toString());
    sw.print(" ");
    sw.print(toWrapperName(field));
    sw.print("(");
    sw.print(beanType.getQualifiedName().toString());
    sw.println(" object) {");
    sw.indent();

    if (beanHelper.hasGetter(field.getSimpleName().toString(), field.asType())) {
      sw.print(
          "return object."
              + asGetter(
                  field.getSimpleName().toString(), field.asType(), context.getAptContext()));
      sw.println("();");
    } else {
      throw new Error("unsupport operation");
    }
    // }-*/;
    sw.outdent();
    sw.println("}");
  }

  private void writeGetBeanMetadata(SourceWriter sw) {
    // public BeanMetadata getBeanMetadata() {
    sw.println("public BeanMetadata getBeanMetadata() {");
    sw.indent();

    // return beanMetadata;
    sw.println("return beanMetadata;");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeGetDescriptor(SourceWriter sw) {
    // public GwtBeanDescriptor<beanType>
    //     getConstraints(ValidationGroupsMetadata validationGroupsMetadata) {
    sw.print("public ");
    sw.print("GwtBeanDescriptor<" + beanHelper.getTypeCanonicalName() + "> ");
    sw.println("getConstraints(ValidationGroupsMetadata validationGroupsMetadata) {");
    sw.indent();

    // beanDescriptor.setValidationGroupsMetadata(validationGroupsMetadata);
    sw.println("beanDescriptor.setValidationGroupsMetadata(validationGroupsMetadata);");

    // return beanDescriptor;
    sw.println("return beanDescriptor;");

    sw.outdent();
    sw.println("}");
  }

  private void writeGetterWrapperMethod(SourceWriter sw, ExecutableElement method) {
    writeUnsafeNativeLongIfNeeded(sw, method.getReturnType());

    // private native fieldType _getter(Bean object) /*={
    sw.print("private native ");
    sw.print(MoreTypes.asTypeElement(method.getReturnType()).getQualifiedName().toString());
    sw.print(" ");
    sw.print(toWrapperName(method));
    sw.print("(");
    sw.print(beanType.getSimpleName().toString());
    sw.println(" object) /*-{");
    sw.indent();

    // return object.@com.examples.Bean::myMethod()();
    sw.print("return object.");
    // sw.print(method.getJsniSignature());
    sw.print("fix me here");
    sw.println("();");

    // }-*/;
    sw.outdent();
    sw.println("}-*/;");
  }

  private void writeIfPropertyNameNotFound(SourceWriter sw) {
    // if (!ALL_PROPERTY_NAMES.contains(propertyName)) {
    sw.println(" if (!ALL_PROPERTY_NAMES.contains(propertyName)) {");
    sw.indent();

    // throw new IllegalArgumentException(propertyName
    // +"is not a valid property of myClass");
    sw.print("throw new ");
    sw.print(IllegalArgumentException.class.getCanonicalName());
    sw.print("( propertyName +\" is not a valid property of ");
    sw.print(beanType.getQualifiedName().toString());
    sw.println("\");");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeNewAnnotation(SourceWriter sw, ConstraintDescriptor constraint) {
    String annotation =
        MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
            .getQualifiedName()
            .toString();

    // new MyAnnotation () {
    sw.print("new ");
    sw.print(annotation);
    sw.println("(){");
    sw.indent();
    sw.indent();

    // public Class<? extends Annotation> annotationType() { return
    // MyAnnotation.class; }
    sw.print("public Class<? extends Annotation> annotationType() {  return ");
    sw.print(annotation);
    sw.println(".class; }");

    constraint
        .getAttributes()
        .forEach(
            (name, holder) -> {
              String maybeValue = holder.value.toString();

              sw.print("public ");
              sw.print(holder.type);
              // generics
              sw.print(" ");
              sw.print(name);
              sw.print("() { return ");

              if ("groups".equals(name) && maybeValue.equals("new java.lang.Class[] {}")) {
                sw.print("new Class[]{javax.validation.groups.Default.class}");
              } else {
                sw.print(maybeValue);
              }

              sw.println(";}");
            });

    sw.outdent();
    sw.outdent();
    sw.println("}");
  }

  private String prepareAnnotation(DefaultValueHolder holder) {
    StringBuffer sb = new StringBuffer();
    sb.append(" ");
    if (holder.value.getClass().isArray()) {
      sb.append("new ");
      if (holder.enumArrayType != null) {
        sb.append(holder.type);
      } else {
        sb.append(
            holder.type.contains("?") ? holder.value.getClass().getCanonicalName() : holder.type);
      }
      sb.append("{");
      Object[] asArray = (Object[]) holder.value;
      sb.append(
          Arrays.stream(asArray)
              .map(GwtSpecificValidatorCreator::asLiteral)
              .collect(Collectors.joining(",")));
      sb.append("}");
    } else {
      sb.append(asLiteral(holder.value));
    }
    return sb.toString();
  }

  private void writeNewViolations(SourceWriter sw, String violationName) {
    // Set<ConstraintViolation<T>> violations =
    sw.print("Set<ConstraintViolation<T>> ");
    sw.print(violationName);
    sw.println(" = ");
    sw.indent();
    sw.indent();

    // new HashSet<ConstraintViolation<T>>();
    sw.println("new HashSet<ConstraintViolation<T>>();");
    sw.outdent();
    sw.outdent();
  }

  /**
   * @param sw
   * @param p
   */
  private void writePropertyDescriptor(SourceWriter sw, PropertyDescriptor p) {
    // private final PropertyDescriptor myProperty_pd =
    sw.print("private final ");
    sw.print(PropertyDescriptorImpl.class.getCanonicalName());
    sw.print(" ");
    sw.print(p.getPropertyName());
    sw.println("_pd =");
    sw.indent();
    sw.indent();

    // new PropertyDescriptorImpl(
    sw.println("new " + PropertyDescriptorImpl.class.getCanonicalName() + "(");
    sw.indent();
    sw.indent();

    // "myProperty",
    sw.println("\"" + p.getPropertyName() + "\",");

    // MyType.class,
    sw.println(p.getFullyQualifiedFieldName() + ".class,");

    // isCascaded,
    // sw.print(Boolean.toString(p.isCascaded()) + ",");
    sw.print(p.isCascaded() + ",");

    // beanMetadata,
    sw.print("beanMetadata");

    // myProperty_c0,
    // myProperty_c1 );
    int count = 0;
    for (ConstraintDescriptor constraint : p.getConstraintDescriptors()) {
      if (areConstraintDescriptorGroupsValid(constraint)
          && !constraint
              .getAnnotation()
              .toString()
              .equals(javax.validation.Valid.class.getCanonicalName())) {
        sw.println(","); // Print the , for the previous line
        sw.print(constraintDescriptorVar(p.getPropertyName(), count));
        count++;
      }
    }
    sw.println(");");

    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();
  }

  private void writePropertyValidators(SourceWriter sw) throws UnableToCompleteException {
    for (PropertyDescriptor p : beanHelper.getBeanDescriptor().getConstrainedProperties()) {
      if (beanHelper.hasField(p)) {
        writeValidatePropertyMethod(sw, p, true);
        sw.println();
      }
      if (beanHelper.hasGetter(p)) {
        writeValidatePropertyMethod(sw, p, false);
        sw.println();
      }
    }
  }

  private void writeValidateAllNonInheritedProperties(SourceWriter sw) {
    // private <T> void validateAllNonInheritedProperties(
    sw.println("private <T> void validateAllNonInheritedProperties(");
    sw.indent();
    sw.indent();

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Class<?>... groups) {
    sw.println("GwtValidationContext<T> context,");
    sw.println(beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    for (PropertyDescriptor p : beanHelper.getBeanDescriptor().getConstrainedProperties()) {
      writeValidatePropertyCall(sw, p, false, true);
    }

    sw.outdent();
    sw.println("}");
  }

  private void writeValidateClassGroups(SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validateClassGroups(
    sw.println("public <T> void validateClassGroups(");

    // GwtValidationContext<T> context, BeanType object,
    // Set<ConstraintViolation<T>> violations, Group... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(beanHelper.getTypeCanonicalName() + " object,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    // /// For each group

    // TODO(nchalko) handle the sequence in the AbstractValidator

    // See JSR 303 section 3.5
    // all reachable fields
    // all reachable getters (both) at once
    // including all reachable and cascadable associations

    sw.println("validateAllNonInheritedProperties(context, object, violations, groups);");

    // validate super classes and super interfaces
    TypeElement object =
        context.getAptContext().elements.getTypeElement(Object.class.getCanonicalName());
    if (!context
        .getAptContext()
        .types
        .isSameType(object.asType(), beanHelper.getClazz().asType())) {
      writeValidateInheritance(sw, beanHelper.getClazz(), Stage.OBJECT, null, false, "groups");
    }
    writeClassLevelConstraintsValidation(sw, "groups");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateConstraint(
      SourceWriter sw,
      PropertyDescriptor p,
      TypeMirror elementClass,
      ConstraintDescriptor constraint,
      String constraintDescriptorVar)
      throws UnableToCompleteException {
    writeValidateConstraint(
        sw, p, elementClass, constraint, constraintDescriptorVar, DEFAULT_VIOLATION_VAR);
  }

  /**
   * Writes the call to actually validate a constraint, including its composite constraints.
   *
   * <p>If the constraint is annotated as {@link javax.validation.ReportAsSingleViolation
   * ReportAsSingleViolation}, then is called recursively and the {@code violationsVar} is changed
   * to match the {@code constraintDescriptorVar}.
   *
   * @param sw the Source Writer
   * @param p the property
   * @param elementClass The class of the Element
   * @param constraint the constraint to validate.
   * @param constraintDescriptorVar the name of the constraintDescriptor variable.
   * @param violationsVar the name of the variable to hold violations
   * @throws UnableToCompleteException
   */
  private void writeValidateConstraint(
      SourceWriter sw,
      PropertyDescriptor p,
      TypeMirror elementClass,
      ConstraintDescriptor constraint,
      String constraintDescriptorVar,
      String violationsVar)
      throws UnableToCompleteException {
    boolean isComposite = !constraint.getComposingConstraints().isEmpty();
    boolean firstReportAsSingleViolation =
        constraint.isReportAsSingleViolation()
            && violationsVar.equals(DEFAULT_VIOLATION_VAR)
            && isComposite;
    boolean reportAsSingleViolation =
        firstReportAsSingleViolation || !violationsVar.equals(DEFAULT_VIOLATION_VAR);
    boolean hasValidator = !constraint.getConstraintValidatorClasses().isEmpty();
    String compositeViolationsVar = constraintDescriptorVar + "_violations";

    // Only do this the first time in a constraint composition.
    if (firstReportAsSingleViolation) {
      // Report myConstraint as Single Violation
      sw.print("// Report ");
      sw.print(
          MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
              .getQualifiedName()
              .toString());
      sw.println(" as Single Violation");
      writeNewViolations(sw, compositeViolationsVar);
    }

    if (hasValidator) {
      String validatorClass;
      try {
        validatorClass = getValidatorForType(context.getAptContext(), constraint, elementClass);
      } catch (UnexpectedTypeException e) {
        throw error(logger, e);
      }

      if (firstReportAsSingleViolation) {
        // if (!
        sw.println("if (!");
        sw.indent();
        sw.indent();
      }

      // validate(myContext, violations object, value, new MyValidator(),
      // constraintDescriptor, groups));
      sw.print("validate(myContext, ");
      sw.print(violationsVar);
      sw.print(", object, value, ");
      sw.print("new ");
      sw.print(validatorClass);
      sw.print("(), ");
      sw.print(constraintDescriptorVar);
      sw.print(", groups)");
      if (firstReportAsSingleViolation) {
        // ) {
        sw.println(") {");
        sw.outdent();
      } else if (!reportAsSingleViolation) {
        // ;
        sw.println(";");
      } else if (isComposite) {
        // ||
        sw.println(" ||");
      }
    } else if (!isComposite
        && !constraint.getAnnotation().toString().equals(Valid.class.getCanonicalName())) {
      // TODO(nchalko) What does the spec say to do here.
      logger.log(
          TreeLogger.WARN,
          "No ConstraintValidator of "
              + constraint.getAnnotation()
              + " for "
              + p.getPropertyName()
              + " of type "
              + elementClass);
    }

    if (firstReportAsSingleViolation) {
      // if (
      sw.print("if (");
      sw.indent();
      sw.indent();
    }
    int count = 0;

    for (ConstraintDescriptor compositeConstraint : constraint.getComposingConstraints()) {
      String compositeVar = constraintDescriptorVar + "_" + count++;
      writeValidateConstraint(
          sw,
          p,
          elementClass,
          compositeConstraint,
          compositeVar,
          firstReportAsSingleViolation ? compositeViolationsVar : violationsVar);
      if (!reportAsSingleViolation) {
        // ;
        sw.println(";");
      } else {
        // ||
        sw.println(" ||");
      }
    }
    if (isComposite && reportAsSingleViolation) {
      // false
      sw.print("false");
    }
    if (firstReportAsSingleViolation) {
      // ) {
      sw.println(" ) {");
      sw.outdent();

      // addSingleViolation(myContext, violations, object, value,
      // constraintDescriptor);
      sw.print("addSingleViolation(myContext, violations, object, value, ");
      sw.print(constraintDescriptorVar);
      sw.println(");");

      // }
      sw.outdent();
      sw.println("}");

      if (hasValidator) {
        // }
        sw.outdent();
        sw.println("}");
      }
    }
  }

  private void writeValidateFieldCall(
      SourceWriter sw, PropertyDescriptor p, boolean useValue, boolean honorValid) {
    String propertyName = p.getPropertyName();

    // validateProperty_<<field>>(context,
    sw.print(validateMethodFieldName(p));
    sw.print("(context, ");
    sw.print("violations, ");

    // null, (MyType) value,
    // or
    // object, object.getLastName(),
    if (useValue) {
      sw.print("null, ");
      sw.print("(");
      sw.print(getQualifiedSourceNonPrimitiveType(beanHelper.getElementType(p, true)));
      sw.print(") value");
    } else {
      sw.print("object, ");
      VariableElement field = beanHelper.getField(propertyName);
      if (field.getModifiers().contains(Modifier.PUBLIC)) {
        sw.print("object.");
        sw.print(propertyName);
      } else {
        fieldsToWrap.add(field);
        sw.print(toWrapperName(field) + "(object)");
      }
    }
    sw.print(", ");

    // honorValid, groups);
    sw.print(Boolean.toString(honorValid));
    sw.println(", groups);");
  }

  private void writeValidateGetterCall(
      SourceWriter sw, PropertyDescriptor p, boolean useValue, boolean honorValid) {
    // validateProperty_get<<field>>(context, violations,
    sw.print(validateMethodGetterName(p));
    sw.print("(context, ");
    sw.print("violations, ");

    // object, object.getMyProp(),
    // or
    // null, (MyType) value,
    if (useValue) {
      sw.print("null, ");
      sw.print("(");
      sw.print(getQualifiedSourceNonPrimitiveType(beanHelper.getElementType(p, false)));
      sw.print(") value");
    } else {
      sw.print("object, ");
      ExecutableElement method =
          beanHelper.findMethod(asGetter(p, context.getAptContext()), Collections.emptyList());
      if (method.getModifiers().contains(Modifier.PUBLIC)) {
        sw.print("object.");
        sw.print(asGetter(p, context.getAptContext()));
        sw.print("()");
      } else {
        gettersToWrap.add(method);
        sw.print(toWrapperName(method) + "(object)");
      }
    }
    sw.print(", ");

    // honorValid, groups);
    sw.print(Boolean.toString(honorValid));
    sw.println(", groups);");
  }

  private void writeValidateInheritance(
      SourceWriter sw, TypeElement clazz, Stage stage, PropertyDescriptor property)
      throws UnableToCompleteException {
    writeValidateInheritance(sw, clazz, stage, property, false, "groups");
  }

  private void writeValidateInheritance(
      SourceWriter sw,
      TypeElement clazz,
      Stage stage,
      PropertyDescriptor property,
      boolean expandDefaultGroupSequence,
      String groupsVarName)
      throws UnableToCompleteException {
    writeValidateInterfaces(sw, clazz, stage, property, expandDefaultGroupSequence, groupsVarName);
    Optional<TypeMirror> maybeParent = findParent(clazz.getSuperclass());
    if (maybeParent.isPresent()) {
      writeValidatorCall(
          sw,
          MoreTypes.asTypeElement(maybeParent.get()),
          stage,
          property,
          expandDefaultGroupSequence,
          groupsVarName);
    }
  }

  private Optional<TypeMirror> findParent(TypeMirror bean) {
    if (!(context.getAptContext().types.isSameType(object.asType(), bean)
        || bean.getKind().equals(TypeKind.NONE)
        || MoreTypes.asTypeElement(bean).getKind().isInterface()
        || MoreTypes.asTypeElement(bean).getModifiers().contains(Modifier.ABSTRACT))) {
      Set<VariableElement> fields =
          MoreTypes.asTypeElement(bean).getEnclosedElements().stream()
              .filter(elm -> elm.getKind().equals(ElementKind.FIELD))
              .map(MoreElements::asVariable)
              .collect(Collectors.toSet());
      for (VariableElement field : fields) {
        for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
          if (context
              .getAptContext()
              .isSupported(annotationMirror.getAnnotationType().asElement().toString())) {
            return Optional.of(bean);
          }
        }
      }
    } else {
      return Optional.empty();
    }
    return findParent(MoreTypes.asTypeElement(bean).getSuperclass());
  }

  private void writeValidateInterfaces(
      SourceWriter sw,
      TypeElement clazz,
      Stage stage,
      PropertyDescriptor p,
      boolean expandDefaultGroupSequence,
      String groupsVarName)
      throws UnableToCompleteException {
    for (TypeMirror type : clazz.getInterfaces()) {
      writeValidatorCall(
          sw, MoreTypes.asTypeElement(type), stage, p, expandDefaultGroupSequence, groupsVarName);
      writeValidateInterfaces(
          sw, MoreTypes.asTypeElement(type), stage, p, expandDefaultGroupSequence, groupsVarName);
    }
  }

  private void writeValidateIterable(SourceWriter sw, PropertyDescriptor p) {
    // int i = 0;
    sw.println("int i = 0;");

    // for (Object instance : value) {
    sw.println("for(Object instance : value) {");
    sw.indent();

    // if(instance != null && !context.alreadyValidated(instance)) {
    sw.println(" if(instance != null  && !context.alreadyValidated(instance)) {");
    sw.indent();

    // violations.addAll(
    sw.println("violations.addAll(");
    sw.indent();
    sw.indent();

    // context.getValidator().validate(
    sw.println("context.getValidator().validate(");
    sw.indent();
    sw.indent();

    TypeElement elementClass = MoreTypes.asTypeElement(p.getElementClass());
    TypeElement list =
        context.getAptContext().elements.getTypeElement(List.class.getCanonicalName());
    if (elementClass.asType().getKind().equals(TypeKind.ARRAY)
        || context.getAptContext().types.isAssignable(elementClass.asType(), list.asType())) {
      // context.appendIndex("myProperty",i++),
      sw.print("context.appendIndex(\"");
      sw.print(p.getPropertyName());
      sw.println("\",i),");
    } else {
      // context.appendIterable("myProperty"),
      sw.print("context.appendIterable(\"");
      sw.print(p.getPropertyName());
      sw.println("\"),");
    }

    // instance, groups));
    sw.println("instance, groups));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");

    // i++;
    sw.println("i++;");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateMap(SourceWriter sw, PropertyDescriptor p) {
    // for (Entry<?, Type> entry : value.entrySet()) {
    sw.print("for(");
    sw.print(Entry.class.getCanonicalName());
    sw.println("<?, ?> entry : value.entrySet()) {");
    sw.indent();

    // if(entry.getValue() != null &&
    // !context.alreadyValidated(entry.getValue())) {
    sw.println(" if(entry.getValue() != null && !context.alreadyValidated(entry.getValue())) {");
    sw.indent();

    // violations.addAll(
    sw.println("violations.addAll(");
    sw.indent();
    sw.indent();

    // context.getValidator().validate(
    sw.println("context.getValidator().validate(");
    sw.indent();
    sw.indent();

    // context.appendKey("myProperty",entry.getKey()),
    sw.print("context.appendKey(\"");
    sw.print(p.getPropertyName());
    sw.println("\",entry.getKey()),");

    // entry.getValue(), groups));
    sw.println("entry.getValue(), groups));");
    sw.outdent();
    sw.outdent();
    sw.outdent();
    sw.outdent();

    // }
    sw.outdent();
    sw.println("}");

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidatePropertyCall(
      SourceWriter sw, PropertyDescriptor property, boolean useValue, boolean honorValid) {
    if (useValue) {
      // boolean valueTypeMatches = false;
      sw.println("boolean valueTypeMatches = false;");
    }
    String getter =
        GwtSpecificValidatorCreator.asGetter(
            property.getPropertyName(), beanHelper.getClazz().asType(), context.getAptContext());

    boolean isLocal =
        ElementFilter.methodsIn(beanHelper.getClazz().getEnclosedElements()).stream()
                .filter(method -> method.getSimpleName().toString().equals(getter))
                .count()
            > 0;

    if (beanHelper.hasGetter(property)) {
      if (isLocal) {
        if (useValue) {
          // if ( value == null || value instanceof propertyType) {
          sw.print("if ( value == null || value instanceof ");
          sw.print(getQualifiedSourceNonPrimitiveType(beanHelper.getElementType(property, false)));
          sw.println(") {");
          sw.indent();

          // valueTypeMatches = true;
          sw.println("valueTypeMatches = true;");
        }
        // validate_getMyProperty
        writeValidateGetterCall(sw, property, useValue, honorValid);
        if (useValue) {
          // }
          sw.outdent();
          sw.println("}");
        }
      }
    }

    if (beanHelper.hasField(property)) {
      if (useValue) {
        // if ( value == null || value instanceof propertyType) {
        sw.print("if ( value == null || value instanceof ");
        sw.print(getQualifiedSourceNonPrimitiveType(beanHelper.getElementType(property, true)));
        sw.println(") {");
        sw.indent();

        // valueTypeMatches = true;
        sw.println("valueTypeMatches = true;");
      }
      // validate_myProperty
      writeValidateFieldCall(sw, property, useValue, honorValid);
      if (useValue) {
        // } else
        sw.outdent();
        sw.println("}");
      }
    }
    if (isLocal) {
      if (useValue & (beanHelper.hasGetter(property) || beanHelper.hasField(property))) {
        // if(!valueTypeMatches) {
        sw.println("if(!valueTypeMatches)  {");
        sw.indent();

        // throw new ValidationException(value.getClass +
        // " is not a valid type for " + propertyName);
        sw.print("throw new ValidationException");
        sw.println("(value.getClass() +\" is not a valid type for \"+ propertyName);");

        // }
        sw.outdent();
        sw.println("}");
      }
    }
  }

  private void writeValidatePropertyGroups(SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validatePropertyGroups(
    sw.println("public <T> void validatePropertyGroups(");

    // GwtValidationContext<T> context, BeanType object, String propertyName,
    // Set<ConstraintViolation<T>> violations, Class<?>... groups) throws ValidationException {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println(beanHelper.getTypeCanonicalName() + " object,");
    sw.println("String propertyName,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) throws ValidationException {");
    sw.outdent();

    for (PropertyDescriptor property : beanHelper.getBeanDescriptor().getConstrainedProperties()) {
      // if (propertyName.equals(myPropety)) {
      sw.print("if (propertyName.equals(\"");
      sw.print(property.getPropertyName());
      sw.println("\")) {");
      sw.indent();

      if (!property.getElementClass().getKind().isPrimitive()
          && !isIterableOrMap(context.getAptContext(), property.getElementClass())
          && !property.getElementClass().getKind().equals(TypeKind.ARRAY)) {
        writeValidatePropertyCall(sw, property, false, false);
        // validate all super classes and interfaces
        writeValidateInheritance(sw, beanHelper.getClazz(), Stage.PROPERTY, property);
      }
      // }
      sw.outdent();
      sw.print("} else ");
    }

    writeIfPropertyNameNotFound(sw);

    // }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidatePropertyMethod(SourceWriter sw, PropertyDescriptor p, boolean useField)
      throws UnableToCompleteException {
    TypeMirror elementClass = p.getElementClass();
    TypeMirror elementType = beanHelper.getElementType(p, useField);

    // private final <T> void validateProperty_{get}<p>(
    sw.print("private final <T> void ");
    if (useField) {
      sw.print(validateMethodFieldName(p));
    } else {
      sw.print(validateMethodGetterName(p));
    }
    sw.println("(");
    sw.indent();
    sw.indent();

    // final GwtValidationContext<T> context,
    sw.println("final GwtValidationContext<T> context,");

    // final Set<ConstraintViolation<T>> violations,
    sw.println("final Set<ConstraintViolation<T>> violations,");

    // BeanType object,
    sw.println(beanHelper.getTypeCanonicalName() + " object,");

    // final <Type> value,
    sw.print("final ");
    // sw.print(elementType.getParameterizedQualifiedSourceName());
    sw.print(elementType.toString());
    sw.println(" value,");

    // boolean honorValid,
    sw.println("boolean honorValid,");

    // Class<?>... groups) {
    sw.println("Class<?>... groups) {");
    sw.outdent();

    // only write the checks if the property is constrained in some way
    if (isPropertyConstrained(p, useField)) {
      // context = context.append("myProperty");
      sw.print("final GwtValidationContext<T> myContext = context.append(\"");
      sw.print(p.getPropertyName());
      sw.println("\");");

      // only check this property if the TraversableResolver says we can

      // Node leafNode = myContext.getPath().getLeafNode();
      sw.println("Node leafNode = myContext.getPath().getLeafNode();");
      // PathImpl path = myContext.getPath().getPathWithoutLeafNode();
      sw.println("PathImpl path = myContext.getPath().getPathWithoutLeafNode();");
      // boolean isReachable;
      sw.println("boolean isReachable;");
      // try {
      sw.println("try {");
      sw.indent();
      // isReachable = myContext.getTraversableResolver().isReachable(object, leafNode,
      //       myContext.getRootBeanClass(), path, ElementType);
      sw.println(
          "isReachable = myContext.getTraversableResolver().isReachable(object, "
              + "leafNode, myContext.getRootBeanClass(), path, "
              + (useField ? asLiteral(ElementType.FIELD) : asLiteral(ElementType.METHOD))
              + ");");
      // } catch (Exception e) {
      sw.outdent();
      sw.println("} catch (Exception e) {");
      sw.indent();
      // throw new ValidationException("TraversableResolver isReachable caused an exception", e);
      sw.println(
          "throw new ValidationException(\"TraversableResolver isReachable caused an "
              + "exception\", e);");
      // }
      sw.outdent();
      sw.println("}");
      // if (isReachable) {
      sw.println("if (isReachable) {");
      sw.indent();

      // TODO(nchalko) move this out of here to the Validate method

      if (p.isCascaded() && hasValid(p, useField)) {

        // if (honorValid && value != null) {
        sw.println("if (honorValid && value != null) {");
        sw.indent();
        // boolean isCascadable;
        sw.println("boolean isCascadable;");
        // try {
        sw.println("try {");
        sw.indent();
        // isCascadable = myContext.getTraversableResolver().isCascadable(object, leafNode,
        //       myContext.getRootBeanClass(), path, ElementType)
        sw.println(
            "isCascadable = myContext.getTraversableResolver().isCascadable(object, "
                + "leafNode, myContext.getRootBeanClass(), path, "
                + (useField ? asLiteral(ElementType.FIELD) : asLiteral(ElementType.METHOD))
                + ");");
        // } catch (Exception e) {
        sw.outdent();
        sw.println("} catch (Exception e) {");
        sw.indent();
        // throw new ValidationException("TraversableResolver isReachable caused an exception", e);
        sw.println(
            "throw new ValidationException(\"TraversableResolver isCascadable caused an "
                + "exception\", e);");
        // }
        sw.outdent();
        sw.println("}");
        // if (isCascadable) {
        sw.println("if (isCascadable) {");
        sw.indent();

        if (isIterableOrMap(context.getAptContext(), elementClass)) {
          TypeElement associationType = beanHelper.getAssociationType(p, useField);
          createBeanHelper(associationType);
          TypeMirror map =
              context
                  .getAptContext()
                  .elements
                  .getTypeElement(Map.class.getCanonicalName())
                  .asType();
          if (context.getAptContext().types.isAssignable(map, p.getElementClass())) {
            writeValidateMap(sw, p);
          } else {
            writeValidateIterable(sw, p);
          }
        } else {
          createBeanHelper(MoreTypes.asTypeElement(elementClass));

          // if (!context.alreadyValidated(value)) {
          sw.println(" if (!context.alreadyValidated(value)) {");
          sw.indent();

          // violations.addAll(myContext.getValidator().validate(context, value,
          // groups));
          sw.print("violations.addAll(");
          sw.println("myContext.getValidator().validate(myContext, value, groups));");

          // }
          sw.outdent();
          sw.println("}");
        }

        // }
        sw.outdent();
        sw.println("}");
        // }
        sw.outdent();
        sw.println("}");
      }

      // It is possible for an annotation with the exact same values to be set on
      // both the field and the getter.
      // Keep track of the ones we have used to make sure we don't duplicate.
      Set<Object> includedAnnotations = Sets.newHashSet();
      int count = 0;

      for (ConstraintDescriptor constraint : p.getConstraintDescriptors()) {
        if (areConstraintDescriptorGroupsValid(constraint)) {
          String annotation =
              MoreElements.asType(constraint.getAnnotation().getAnnotationType().asElement())
                  .getQualifiedName()
                  .toString();
          if (!annotation.equals(Valid.class.getCanonicalName())) {
            if (hasMatchingAnnotation(p, useField, constraint)) {

              String constraintDescriptorVar = constraintDescriptorVar(p.getPropertyName(), count);

              if (!includedAnnotations.contains(annotation)) {
                if (useField) {
                  writeValidateConstraint(sw, p, elementClass, constraint, constraintDescriptorVar);
                } else {
                  // The annotation hasn't been looked at twice (yet) and we are validating a getter
                  // Write the call if only the getter has this constraint applied to it
                  boolean hasField = beanHelper.hasField(p);
                  if (!hasField || (hasField && !hasMatchingAnnotation(p, true, constraint))) {
                    writeValidateConstraint(
                        sw, p, elementClass, constraint, constraintDescriptorVar);
                  }
                }
              } else {
                // The annotation has been looked at once already during this validate property call
                // so we know the field and the getter are both annotated with the same constraint.
                if (!useField) {
                  writeValidateConstraint(sw, p, elementClass, constraint, constraintDescriptorVar);
                }
              }
              includedAnnotations.add(annotation);
            }
            count++;
          }
        }
      }
      // }
      sw.outdent();
      sw.println("}");
    }
    sw.outdent();
    sw.println("}");
  }

  private void writeValidateValueGroups(SourceWriter sw) throws UnableToCompleteException {
    // public <T> void validateValueGroups(
    sw.println("public <T> void validateValueGroups(");

    // GwtValidationContext<T> context, Class<Author> beanType, String propertyName,
    // Object value, Set<ConstraintViolation<T>> violations, Class<?>... groups) {
    sw.indent();
    sw.indent();
    sw.println("GwtValidationContext<T> context,");
    sw.println("Class<" + beanHelper.getTypeCanonicalName() + "> beanType,");
    sw.println("String propertyName,");
    sw.println("Object value,");
    sw.println("Set<ConstraintViolation<T>> violations,");
    sw.println("Class<?>... groups) {");
    sw.outdent();

    for (PropertyDescriptor property : beanHelper.getBeanDescriptor().getConstrainedProperties()) {
      // if (propertyName.equals(myPropety)) {
      sw.print("if (propertyName.equals(\"");
      sw.print(property.getPropertyName());
      sw.println("\")) {");
      sw.indent();

      if (!isIterableOrMap(
          context.getAptContext(), beanHelper.getField(property.getPropertyName()).asType())) {
        writeValidatePropertyCall(sw, property, true, false);
      }

      // validate all super classes and interfaces
      writeValidateInheritance(sw, beanHelper.getClazz(), Stage.VALUE, property);

      // }
      sw.outdent();
      sw.print("} else ");
    }

    writeIfPropertyNameNotFound(sw);

    sw.outdent();
    sw.println("}");
  }

  /**
   * @param p Only used if writing a call to validate a property - otherwise can be null.
   * @param expandDefaultGroupSequence Only used if writing a call to validate a bean.
   * @param groupsVarName The name of the variable containing the groups.
   */
  private void writeValidatorCall(
      SourceWriter sw,
      TypeElement type,
      Stage stage,
      PropertyDescriptor p,
      boolean expandDefaultGroupSequence,
      String groupsVarName)
      throws UnableToCompleteException {
    if (!isIterableOrMap(context.getAptContext(), type.asType())) {
      BeanHelper helper = createBeanHelper(type);
      beansToValidate.add(helper);
      switch (stage) {
        case OBJECT:
          if (type.getKind().isInterface() || type.getModifiers().contains(Modifier.ABSTRACT)) {
            return;
          }
          // myValidator
          sw.print(helper.getValidatorInstanceName());
          if (expandDefaultGroupSequence) {
            // .expandDefaultAndValidateClassGroups(context,object,violations,groups);
            sw.println(
                ".expandDefaultAndValidateClassGroups(context, object, violations, "
                    + groupsVarName
                    + ");");
          } else {
            // .validateClassGroups(context,object,violations,groups);
            sw.println(".validateClassGroups(context, object, violations, " + groupsVarName + ");");
          }
          break;
        case PROPERTY:
          if (isPropertyConstrained(helper, p)) {
            // myValidator.validatePropertyGroups(context,object
            // ,propertyName, violations, groups);
            sw.print(helper.getValidatorInstanceName());
            sw.print(".validatePropertyGroups(context, object,");
            sw.println(" propertyName, violations, " + groupsVarName + ");");
          }
          break;
        case VALUE:
          if (isPropertyConstrained(helper, p)) {
            // myValidator.validateValueGroups(context,beanType
            // ,propertyName, value, violations, groups);
            sw.print(helper.getValidatorInstanceName());
            sw.print(".validateValueGroups(context, ");
            sw.print(helper.getTypeCanonicalName());
            sw.println(".class, propertyName, value, violations, " + groupsVarName + ");");
          }
          break;
        default:
          throw new IllegalStateException();
      }
    }
  }

  private void writeWrappers(SourceWriter sw) {
    sw.println("// Write the wrappers after we know which are needed");
    for (VariableElement field : fieldsToWrap) {
      writeFieldWrapperMethod(sw, field);
      sw.println();
    }

    for (ExecutableElement method : gettersToWrap) {
      writeGetterWrapperMethod(sw, method);
      sw.println();
    }
  }

  private enum Stage {
    OBJECT,
    PROPERTY,
    VALUE
  }
}

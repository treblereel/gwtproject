/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.validation.rebind.beaninfo.impl;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.validation.Valid;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;
import org.gwtproject.validation.rebind.beaninfo.PropertyDescriptor;

/** @author Dmitrii Tikhomirov Created by treblereel 8/20/19 */
public class PropertyDescriptorImpl implements PropertyDescriptor {

  private String propertyName;

  private boolean isCascaded;

  private Element field;

  private Set<AnnotationMirror> annotations;

  private Set<ConstraintDescriptor> constraintDescriptors;

  PropertyDescriptorImpl(Element field, AptContext context) {
    this.field = field;
    this.constraintDescriptors = new HashSet<>();
    for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
      String annotation =
          MoreElements.asType(annotationMirror.getAnnotationType().asElement())
              .getQualifiedName()
              .toString();
      if (context.isSupported(annotation)) {
        ConstraintDescriptorImpl descriptor =
            new ConstraintDescriptorImpl(annotationMirror, field, context);
        constraintDescriptors.add(descriptor);

        context
            .getConstraint(annotation)
            .getInheritedConstraint()
            .forEach(
                i ->
                    descriptor
                        .getComposingConstraints()
                        .add(new ConstraintDescriptorImpl(i, field, context)));
      }
    }
  }

  public static PropertyDescriptorImpl of(
      VariableElement field, Set<AnnotationMirror> annotations, AptContext context) {
    PropertyDescriptorImpl impl = new PropertyDescriptorImpl(field, context);
    impl.propertyName = field.getSimpleName().toString();
    impl.isCascaded = field.getAnnotation(Valid.class) != null;
    impl.annotations = annotations;

    return impl;
  }

  @Override
  public boolean isCascaded() {
    return isCascaded;
  }

  @Override
  public String getPropertyName() {
    return propertyName;
  }

  @Override
  public boolean hasConstraints() {
    return !constraintDescriptors.isEmpty();
  }

  @Override
  public TypeMirror getElementClass() {
    return field.asType();
  }

  @Override
  public String getFullyQualifiedFieldName() {
    return (field.asType().getKind().isPrimitive()
            || field.asType().getKind().equals(TypeKind.ARRAY))
        ? field.asType().toString()
        : MoreTypes.asTypeElement(field.asType()).getQualifiedName().toString();
  }

  @Override
  public Set<ConstraintDescriptor> getConstraintDescriptors() {
    return constraintDescriptors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PropertyDescriptorImpl)) {
      return false;
    }
    PropertyDescriptorImpl that = (PropertyDescriptorImpl) o;
    return isCascaded == that.isCascaded
        && Objects.equals(propertyName, that.propertyName)
        && Objects.equals(field, that.field)
        && Objects.equals(annotations, that.annotations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(propertyName, isCascaded, field, annotations);
  }

  @Override
  public String toString() {
    return "PropertyDescriptorImpl{"
        + "propertyName='"
        + propertyName
        + '\''
        + ", isCascaded="
        + isCascaded
        + ", field="
        + field
        + ", annotations="
        + annotations.stream().map(m -> m.toString()).collect(Collectors.joining(","))
        + '}';
  }
}

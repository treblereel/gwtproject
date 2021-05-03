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
package org.gwtproject.validation.rebind.beaninfo;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.util.HashSet;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.impl.PropertyDescriptorImpl;

/** @author Dmitrii Tikhomirov Created by treblereel 8/20/19 */
public class BeanInfo implements BeanDescriptor {

  private final TypeElement bean;
  private final AptContext context;

  private Set<PropertyDescriptor> propertyDescriptors = new HashSet<>();
  private Set<ConstraintDescriptor> constraintDescriptors = new HashSet<>();

  public BeanInfo(AptContext context, TypeElement bean) {
    this.bean = bean;
    this.context = context;

    for (VariableElement field : ElementFilter.fieldsIn(bean.getEnclosedElements())) {
      Set<AnnotationMirror> annotations = new HashSet<>();
      for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
        String annotation =
            MoreElements.asType(annotationMirror.getAnnotationType().asElement())
                .getQualifiedName()
                .toString();
        if (context.isSupported(annotation)) {
          annotations.add(annotationMirror);
        }
      }
      if (!annotations.isEmpty()) {
        propertyDescriptors.add(PropertyDescriptorImpl.of(field, annotations, context));
      }
    }
  }

  public Set<PropertyDescriptor> getPropertyDescriptors() {
    return propertyDescriptors;
  }

  @Override
  public boolean isBeanConstrained() {
    return false;
  }

  @Override
  public PropertyDescriptor getConstraintsForProperty(String propertyName) {
    return null;
  }

  @Override
  public Set<PropertyDescriptor> getConstrainedProperties() {
    return getPropertyDescriptors();
  }

  @Override
  public boolean hasConstraints() {
    return false;
  }

  @Override
  public TypeElement getElementClass() {
    return MoreTypes.asTypeElement(bean.asType());
  }

  @Override
  public String getFullyQualifiedFieldName() {
    return bean.asType().getKind().isPrimitive()
        ? bean.asType().toString()
        : MoreTypes.asTypeElement(bean.asType()).getQualifiedName().toString();
  }

  @Override
  public Set<ConstraintDescriptor> getConstraintDescriptors() {
    return constraintDescriptors;
  }
}

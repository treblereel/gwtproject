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
import java.util.*;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.groups.Default;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;

/** @author Dmitrii Tikhomirov Created by treblereel 8/20/19 */
public class ConstraintDescriptorImpl implements ConstraintDescriptor {

  private AnnotationMirror annotation;

  private Element source;

  private Map<String, DefaultValueHolder> holder = new HashMap<>();

  private Set<ConstraintDescriptor> composingConstraints = new HashSet<>();

  private AptContext context;

  public ConstraintDescriptorImpl(
      AnnotationMirror annotationMirror, Element source, AptContext context) {
    this.annotation = annotationMirror;
    this.source = source;
    this.context = context;

    for (ExecutableElement method :
        ElementFilter.methodsIn(
            annotationMirror.getAnnotationType().asElement().getEnclosedElements())) {
      AnnotationValue defaultValue = method.getDefaultValue();

      for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
          annotationMirror.getElementValues().entrySet()) {
        if (entry.getKey().getSimpleName().toString().equals(method.getSimpleName().toString())) {
          defaultValue = entry.getValue();
        }
      }

      if (defaultValue.toString().equals("{}")) {
        String val = "new " + context.types.erasure(method.getReturnType()) + " {}";
        holder.put(
            method.getSimpleName().toString(), new DefaultValueHolder(method.getReturnType(), val));
      } else {
        holder.put(
            method.getSimpleName().toString(),
            new DefaultValueHolder(method.getReturnType(), defaultValue));
      }
    }
  }

  @Override
  public AnnotationMirror getAnnotation() {
    return annotation;
  }

  // TODO fix it, it's a workaround
  @Override
  public ClassWrapper[] getGroups() {
    List<String> groups = new ArrayList<>();
    if (holder.get("groups").value.toString().equals("new java.lang.Class[] {}")) {
      groups.add(Default.class.getCanonicalName());
    }
    List<ClassWrapper> classes = new ArrayList<>();
    for (String s : groups) {
      classes.add(new ClassWrapper(s));
    }
    return classes.toArray(new ClassWrapper[classes.size()]);
  }

  @Override
  public Set<Class<? extends Payload>> getPayload() {
    return Collections.emptySet();
  }

  @Override
  public List<String> getConstraintValidatorClasses() {
    return context.getValidators(
        MoreElements.asType(annotation.getAnnotationType().asElement())
            .getQualifiedName()
            .toString());
  }

  @Override
  public Map<String, DefaultValueHolder> getAttributes() {
    return holder;
  }

  @Override
  public Set<ConstraintDescriptor> getComposingConstraints() {
    return composingConstraints;
  }

  @Override
  public boolean isReportAsSingleViolation() {
    return context
            .elements
            .getTypeElement(annotation.getAnnotationType().toString())
            .getAnnotation(ReportAsSingleViolation.class)
        != null;
  }

  public Element getSource() {
    return source;
  }

  public static class ClassWrapper {

    private String clazz;

    public ClassWrapper(String clazz) {
      this.clazz = clazz;
    }

    public String getClazz() {
      return clazz;
    }
  }

  public static class DefaultValueHolder {

    public String type;

    public Object value;

    public boolean isEnumArray = false;

    public TypeElement enumArrayType;

    DefaultValueHolder(String type, Object value) {
      this.type = type;
      this.value = value;
    }

    DefaultValueHolder(TypeMirror type, Object value) {
      this.type = type.toString();
      this.value = value;

      if (type.getKind().equals(TypeKind.ARRAY)) {
        boolean isEnumArray =
            MoreTypes.asTypeElement(MoreTypes.asArray(type).getComponentType())
                .getKind()
                .equals(ElementKind.ENUM);
        if (isEnumArray) {
          this.isEnumArray = true;
          this.enumArrayType = MoreTypes.asTypeElement(MoreTypes.asArray(type).getComponentType());
        }
      }
    }
  }
}

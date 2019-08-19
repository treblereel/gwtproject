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
import com.google.common.base.Function;
import org.gwtproject.validation.rebind.ext.NotFoundException;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.PropertyDescriptor;

/**
 * A simple struct for the various values associated with a Bean that can be
 * validated.
 */
public final class BeanHelper {

  public static final Function<BeanHelper, TypeElement> TO_CLAZZ =
          helper -> helper.getClazz();

  private final BeanDescriptor beanDescriptor;

  private final TypeElement jClass;

  /**
   * Shouldn't be created directly; instead use BeanHelperCache.
   */
  BeanHelper(TypeElement jClass, BeanDescriptor beanDescriptor) {
    this.beanDescriptor = beanDescriptor;
    this.jClass = jClass;
  }

  public TypeElement getAssociationType(PropertyDescriptor p, boolean useField) {
    throw new UnsupportedOperationException("getAssociationType");
/*    JType type = this.getElementType(p, useField);
    JArrayType jArray = type.isArray();
    if (jArray != null) {
      return jArray.getComponentType().isClassOrInterface();
    }
    JParameterizedType pType = type.isParameterized();
    TypeElement[] typeArgs;
    if (pType == null) {
      JRawType rType = type.isRawType();
      typeArgs = rType.getGenericType().getTypeParameters();
    } else {
      typeArgs = pType.getTypeArgs();
    }
    // it is either a Iterable or a Map use the last type arg.
    return typeArgs[typeArgs.length - 1].isClassOrInterface();*/
  }

  public BeanDescriptor getBeanDescriptor() {
    return beanDescriptor;
  }

  /*
   * The server-side validator needs an actual class.
   */
  public TypeElement getClazz() {
    return jClass;
  }

  public String getFullyQualifiedValidatorName() {
    return getPackage() + "." + getValidatorName();
  }

  public TypeElement getJClass() {
    return jClass;
  }

  public String getPackage() {
    return MoreElements.getPackage(jClass).getQualifiedName().toString();
  }

  public String getTypeCanonicalName() {
    return jClass.getQualifiedName().toString();
  }

  public String getValidatorInstanceName() {
    return getFullyQualifiedValidatorName() + ".INSTANCE";
  }

  public String getValidatorName() {
    return makeJavaSafe("_" + jClass.getSimpleName() + "Validator");
  }

  @Override
  public String toString() {
    return getTypeCanonicalName();
  }

  TypeElement getElementType(PropertyDescriptor p, boolean useField) {
    throw new UnsupportedOperationException("getElementType");
/*    if (useField) {
      return jClass.findField(p.getPropertyName()).getType();
    } else {
      return jClass.findMethod(GwtSpecificValidatorCreator.asGetter(p),
          GwtSpecificValidatorCreator.NO_ARGS).getReturnType();
    }*/
  }

  boolean hasField(PropertyDescriptor p) {
    throw new UnsupportedOperationException("hasField");
/*
    VariableElement field = jClass.findField(p.getPropertyName());
    return field != null;*/
  }

  boolean hasGetter(PropertyDescriptor p) {
    throw new UnsupportedOperationException("hasGetter");

/*    JType[] paramTypes = new JType[]{};
    try {
      jClass.getMethod(GwtSpecificValidatorCreator.asGetter(p), paramTypes);
      return true;
    } catch (NotFoundException e) {
      return false;
    }*/
  }

  private String makeJavaSafe(String in) {
    return in.replaceAll("\\.", "_");
  }

}

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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.base.Function;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.BeanDescriptor;
import org.gwtproject.validation.rebind.beaninfo.BeanInfo;
import org.gwtproject.validation.rebind.beaninfo.PropertyDescriptor;
import org.gwtproject.validation.rebind.ext.NotFoundException;

/**
 * A simple struct for the various values associated with a Bean that can be
 * validated.
 */
public final class BeanHelper {

    public static final Function<BeanHelper, TypeMirror> TO_CLAZZ =
            helper -> helper.getClazz().asType();

    private final TypeElement jClass;

    private final BeanInfo beanInfo;

    /**
     * Shouldn't be created directly; instead use BeanHelperCache.
     */
    BeanHelper(AptContext context, TypeElement jClass) {
        this.beanInfo = new BeanInfo(context, jClass);
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
        return beanInfo;
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
        if (useField) {
            return MoreTypes.asTypeElement(getField(p.getPropertyName()).asType());
        } else {
            return MoreTypes.asTypeElement(findMethod(GwtSpecificValidatorCreator.asGetter(p.getPropertyName()),
                                                      Collections.EMPTY_LIST).getReturnType());
        }
    }

    boolean hasField(PropertyDescriptor p) {
        if (jClass.getEnclosedElements()
                .stream()
                .filter(elm -> (elm.getKind().equals(ElementKind.FIELD)))
                .findFirst()
                .isPresent()) {
            return true;
        }
        return false;
    }

    public VariableElement getField(String name) {
        return MoreElements.asVariable(jClass.getEnclosedElements()
                                               .stream()
                                               .filter(elm -> (elm.getKind().equals(ElementKind.FIELD)))
                                               .findFirst().orElseThrow(() -> new Error(
                        new NotFoundException("No field" + name + " presented in " + jClass.getQualifiedName().toString()))));
    }

    boolean hasGetter(String name) {
        String getter = GwtSpecificValidatorCreator.asGetter(name);
        jClass.getEnclosedElements().stream().filter(elm -> (elm.getKind().equals(ElementKind.METHOD)))
                .filter(elm -> (elm).getSimpleName().toString().equals(getter))
                .filter(elm -> MoreElements.asExecutable(elm).getParameters().isEmpty())
                .findFirst()
                .orElseThrow(() -> new Error(
                        new NotFoundException("No " + getter + " presented in " + jClass.getQualifiedName().toString())));
        return true;
    }

    public ExecutableElement findMethod(String name) {
        return MoreElements.asExecutable(jClass.getEnclosedElements().stream().filter(elm -> (elm.getKind().equals(ElementKind.METHOD)))
                                                 .filter(elm -> (elm).getSimpleName().toString().equals(name))
                                                 .findFirst()
                                                 .orElseThrow(() -> new Error(
                                                         new NotFoundException("No method" + name + " presented in " + jClass.getQualifiedName().toString()))));
    }

    public ExecutableElement findMethod(String name, List<? extends VariableElement> params) {

        ExecutableElement method = findMethod(name);
        if (params.isEmpty() && method.getParameters().isEmpty()) {
            return method;
        } else {
            throw new Error("not implemented yet");
        }
    }

    public Set<PropertyDescriptor> getPropertyDescriptors() {
        return beanInfo.getPropertyDescriptors();
    }

    private String makeJavaSafe(String in) {
        return in.replaceAll("\\.", "_");
    }
}

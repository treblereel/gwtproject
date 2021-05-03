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
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.base.Function;
import org.gwtproject.validation.context.AptContext;
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
    private AptContext context;

    /**
     * Shouldn't be created directly; instead use BeanHelperCache.
     */
    BeanHelper(AptContext context, TypeElement jClass) {
        this.beanInfo = new BeanInfo(context, jClass);
        this.jClass = jClass;
        this.context = context;
    }

    public TypeElement getAssociationType(PropertyDescriptor p, boolean useField) {
        TypeMirror type = this.getElementType(p, useField);
        if (type.getKind().equals(TypeKind.ARRAY)) {
            return MoreTypes.asTypeElement(MoreTypes.asArray(type).getComponentType());
        }

        List<? extends TypeParameterElement> params = MoreTypes.asTypeElement(type).getTypeParameters();
        return MoreElements.asType(params.get(params.size() - 1).getGenericElement());
    }

    public BeanInfo getBeanDescriptor() {
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

    TypeMirror getElementType(PropertyDescriptor p, boolean useField) {
        if (useField) {
            return getField(p.getPropertyName()).asType();
        } else {
            return findMethod(GwtSpecificValidatorCreator.asGetter(p),
                              Collections.emptyList()).getReturnType();
        }
    }

    boolean hasField(PropertyDescriptor p) {
        return jClass.getEnclosedElements()
                .stream()
                .filter(elm -> (elm.getKind().equals(ElementKind.FIELD)))
                .filter(field -> MoreElements.asVariable(field)
                        .getSimpleName().toString()
                        .equals(p.getPropertyName())
                ).findFirst().isPresent();
    }

    public VariableElement getField(String name) {
        return MoreElements.asVariable(jClass.getEnclosedElements()
                                               .stream()
                                               .filter(elm -> (elm.getKind().equals(ElementKind.FIELD)))
                                               .filter(field -> field.getSimpleName().toString().equals(name))
                                               .findFirst().orElseThrow(() -> new Error(
                        new NotFoundException("No field" + name + " presented in " + jClass.getQualifiedName().toString()))));
    }

    boolean hasGetter(PropertyDescriptor p) {
        return hasGetter(p.getPropertyName(), p.getElementClass());
    }

    boolean hasGetter(String name, TypeMirror type) {
        String getter = GwtSpecificValidatorCreator.asGetter(name, type);
        jClass.getEnclosedElements().stream().filter(elm -> (elm.getKind().equals(ElementKind.METHOD)))
                .filter(elm -> (elm).getSimpleName().toString().equals(getter))
                .filter(elm -> MoreElements.asExecutable(elm).getParameters().isEmpty())
                .findFirst()
                .orElseThrow(() -> new Error(
                        new NotFoundException("No " + getter + " presented in " + jClass.toString())));
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

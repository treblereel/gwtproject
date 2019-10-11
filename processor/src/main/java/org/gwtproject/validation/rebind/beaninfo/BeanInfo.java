package org.gwtproject.validation.rebind.beaninfo;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.impl.PropertyDescriptorImpl;
import org.gwtproject.validation.rg.util.Util;
import org.gwtproject.validation.rg.util.tools.IsGetter;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/20/19
 */
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
                String annotation = MoreElements.asType(annotationMirror.getAnnotationType()
                                                                .asElement()).getQualifiedName().toString();
                if(context.isSupported(annotation)) {
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
        return bean.asType().getKind().isPrimitive() ? bean.asType().toString() :
                MoreTypes.asTypeElement(bean.asType()).getQualifiedName().toString();    }

    @Override
    public Set<ConstraintDescriptor> getConstraintDescriptors() {
        return constraintDescriptors;
    }

}

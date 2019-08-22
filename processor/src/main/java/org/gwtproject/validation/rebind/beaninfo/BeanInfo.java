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
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.rebind.beaninfo.impl.ConstraintDescriptorImpl;
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

            field.getAnnotationMirrors().forEach(mirror -> {
                System.out.println("field " + field);


                mirror.getElementValues().forEach((v, v1) -> {

                    System.out.println("                   v " + v.getSimpleName().toString() + " " + v1.getValue() + " " + v1.getValue().getClass());

                });
            });



            Set<Annotation> annotations = new HashSet<>();
            for (Class<? extends Annotation> annotation : context.getConstraints()) {
                if (field.getAnnotation(annotation) != null) {
                    annotations.add(field.getAnnotation(annotation));
                }
            }
            if (!annotations.isEmpty()) {
                propertyDescriptors.add(PropertyDescriptorImpl.of(field, annotations));
                //annotations.forEach(a -> constraintDescriptors.add(ConstraintDescriptorImpl.of(context, a)));
            }
        }
    }

    public Optional<? extends AnnotationMirror> getAnnotationMirror(final Element element, final Class<? extends Annotation> annotationClass) {
        final String annotationClassName = annotationClass.getName();
        final Optional<? extends AnnotationMirror> retValue = element.getAnnotationMirrors().stream()
                .filter(m -> m.getAnnotationType().toString().equals(annotationClassName))
                .findFirst();
        return retValue;
    }

    public Collection<VariableElement> getProperties() {
        System.out.println("getProperties " + bean.getQualifiedName().toString());

        Map<String, VariableElement> fields = Util.getAllFieldsIn(context.elements, bean)
                .stream()
                .collect(Collectors.toMap(p -> p.getSimpleName().toString(), p -> p));

        return Util.getAllMethodsIn(context.elements, bean)
                .stream()
                .filter(method -> IsGetter.isGetterMethod(method))
                .map(method -> IsGetter.getVariableName(method)).filter(variable -> fields.containsKey(variable))
                .map(m -> fields.get(m)).collect(Collectors.toSet());
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
    public Set<ConstraintDescriptor> getConstraintDescriptors() {
        return constraintDescriptors;
    }

    @Override
    public ConstraintFinder findConstraints() {
        return null;
    }
}

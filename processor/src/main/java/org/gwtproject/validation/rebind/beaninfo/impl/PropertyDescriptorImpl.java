package org.gwtproject.validation.rebind.beaninfo.impl;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.validation.Valid;

import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;
import org.gwtproject.validation.rebind.beaninfo.PropertyDescriptor;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/20/19
 */
public class PropertyDescriptorImpl implements PropertyDescriptor {

    private String propertyName;

    private boolean isCascaded;

    private Element field;

    private Set<Annotation> annotation;

    private Set<ConstraintDescriptor> constraintDescriptors;

    PropertyDescriptorImpl(Element field) {
        this.field = field;
        this.constraintDescriptors = new HashSet<>();

        for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
            constraintDescriptors.add(new ConstraintDescriptorImpl(annotationMirror, field));
        }
    }

    public static PropertyDescriptorImpl of(VariableElement field, Set<Annotation> annotation) {
        PropertyDescriptorImpl impl = new PropertyDescriptorImpl(field);
        impl.propertyName = field.getSimpleName().toString();
        impl.isCascaded = field.getAnnotation(Valid.class) != null;
        impl.annotation = annotation;

        return impl;
    }

    private boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
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
        return false;
    }

    @Override
    public TypeElement getElementClass() {
        return MoreTypes.asTypeElement(field.asType());
    }

    @Override
    public Set<ConstraintDescriptor> getConstraintDescriptors() {
        return constraintDescriptors;
    }

    @Override
    public ConstraintFinder findConstraints() {
        return null;
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
        return isCascaded == that.isCascaded &&
                Objects.equals(propertyName, that.propertyName) &&
                Objects.equals(field, that.field) &&
                Objects.equals(annotation, that.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyName, isCascaded, field, annotation);
    }

    @Override
    public String toString() {
        return "PropertyDescriptorImpl{" +
                "propertyName='" + propertyName + '\'' +
                ", isCascaded=" + isCascaded +
                ", field=" + field +
                ", annotation=" + annotation +
                '}';
    }
}

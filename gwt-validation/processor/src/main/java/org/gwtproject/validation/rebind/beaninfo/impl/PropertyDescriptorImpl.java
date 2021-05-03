package org.gwtproject.validation.rebind.beaninfo.impl;

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

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.context.AptContext;
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

    private Set<AnnotationMirror> annotations;

    private Set<ConstraintDescriptor> constraintDescriptors;

    PropertyDescriptorImpl(Element field, AptContext context) {
        this.field = field;
        this.constraintDescriptors = new HashSet<>();
        for (AnnotationMirror annotationMirror : field.getAnnotationMirrors()) {
            String annotation = MoreElements.asType(annotationMirror.getAnnotationType().asElement()).getQualifiedName().toString();
            if (context.isSupported(annotation)) {
                constraintDescriptors.add(new ConstraintDescriptorImpl(annotationMirror, field, context));
                context.getConstraint(annotation).getInheritedConstraint().forEach(i -> {
                    constraintDescriptors.add(new ConstraintDescriptorImpl(i, field, context));
                });
            }
        }
    }

    public static PropertyDescriptorImpl of(VariableElement field, Set<AnnotationMirror> annotations, AptContext context) {
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
        return (field.asType().getKind().isPrimitive() ||
                field.asType().getKind().equals(TypeKind.ARRAY)) ? field.asType().toString() :
                MoreTypes.asTypeElement(field.asType()).getQualifiedName().toString();
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
        return isCascaded == that.isCascaded &&
                Objects.equals(propertyName, that.propertyName) &&
                Objects.equals(field, that.field) &&
                Objects.equals(annotations, that.annotations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyName, isCascaded, field, annotations);
    }

    @Override
    public String toString() {
        return "PropertyDescriptorImpl{" +
                "propertyName='" + propertyName + '\'' +
                ", isCascaded=" + isCascaded +
                ", field=" + field +
                ", annotations=" + annotations.stream().map(m -> m.toString()).collect(Collectors.joining(",")) +
                '}';
    }
}

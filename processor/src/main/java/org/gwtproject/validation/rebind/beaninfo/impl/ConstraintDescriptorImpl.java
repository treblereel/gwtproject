package org.gwtproject.validation.rebind.beaninfo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.validation.Payload;
import javax.validation.groups.Default;

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import org.gwtproject.validation.rebind.ConstraintHelper;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/20/19
 */
public class ConstraintDescriptorImpl implements ConstraintDescriptor {

    private AnnotationMirror annotation;

    private Element source;

    private Map<String, Object> holder = new HashMap<>();

    private Map<String, DefaultValueHolder> defaultValues = new HashMap<>();

    public ConstraintDescriptorImpl(AnnotationMirror annotationMirror, Element source) {
        this.annotation = annotationMirror;
        this.source = source;
        for (ExecutableElement meth : ElementFilter.methodsIn(annotationMirror.getAnnotationType()
                                                                      .asElement()
                                                                      .getEnclosedElements())) {
            AnnotationValue defaultValue = meth.getDefaultValue();
            if (defaultValue != null) {
                defaultValues.put(meth.getSimpleName().toString(),
                                  new DefaultValueHolder(meth.getReturnType().toString(),
                                                         parseValue(meth.getReturnType(), defaultValue.getValue())));
            }
        }

        annotationMirror.getElementValues().forEach((k, v) -> {
            this.holder.put(k.getSimpleName().toString(), parseValue(k.getReturnType(), v.getValue()));
        });
    }

    //TODO shitty workarounds
    private static Object parseValue(Object value) {
        if (value.getClass().toString().contains("com.sun.tools.javac.util.List")) {
            List<String> result = new ArrayList<>();
            for (Object object : (java.util.AbstractCollection) value) {
                result.add(object.toString().replaceAll(".class", ""));
            }
            return result.toArray();
        }
        return value;
    }

    private Object parseValue(TypeMirror typeMirror, Object value) {
        if (TypeKind.ARRAY.equals(typeMirror.getKind())) {
            ArrayType arrayType = (ArrayType) typeMirror;
            String componentType = arrayType.getComponentType().toString();
            TypeElement type = MoreTypes.asTypeElement(arrayType.getComponentType());
            type.getKind().equals(ElementKind.ENUM);
            if (type.getKind().equals(ElementKind.ENUM)) {
                List result = new ArrayList<>();
                for (Object clazz : (java.util.AbstractCollection) value) {
                    result.add(clazz);
                }
                return result.toArray(new Object[result.size()]);
            } else if (type.getKind().equals(ElementKind.CLASS)) {
                List<Class> result = new ArrayList<>();
                for (Class clazz : (java.util.AbstractCollection<Class<?>>) value) {
                    result.add(clazz);
                }
                return result.toArray(new Class[result.size()]);
            }

            List<Object> result = new ArrayList<>();
            if (componentType.startsWith("java.lang.Class")) {
                for (Object obj : (java.util.AbstractCollection<Object>) value) {
                    result.add(obj);
                }
            }
            return result.toArray(new Object[result.size()]);
        } else {
            return value;
        }
    }

    @Override
    public String getAnnotation() {
        return MoreElements.asType(annotation.getAnnotationType().asElement()).getQualifiedName().toString();
    }

    @Override
    public ClassWrapper[] getGroups() {
        List<String> groups = new ArrayList<>();

        if (!holder.containsKey("groups")) {
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
        return Collections.EMPTY_SET;
    }

    @Override
    public List<String> getConstraintValidatorClasses() {
        return new ConstraintHelper().get(getAnnotation());
    }

    @Override
    public Map<String, DefaultValueHolder> getAttributes() {
        for (Map.Entry<String, Object> entry : holder.entrySet()) {
            if (defaultValues.containsKey(entry.getKey())) {
                defaultValues.get(entry.getKey()).value = entry.getValue();
            } else {
                defaultValues.put(entry.getKey(),
                                  new DefaultValueHolder(entry.getValue()
                                                                 .getClass()
                                                                 .getCanonicalName(),
                                                         entry.getValue()));
            }
        }
        return defaultValues;
    }

    @Override
    public Set<ConstraintDescriptor> getComposingConstraints() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isReportAsSingleViolation() {
        return false;
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

        DefaultValueHolder(String type, Object value) {
            this.type = type;
            this.value = value;
        }
    }
}

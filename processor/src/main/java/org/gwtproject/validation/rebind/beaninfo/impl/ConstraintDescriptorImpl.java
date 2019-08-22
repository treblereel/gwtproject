package org.gwtproject.validation.rebind.beaninfo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.validation.Payload;
import javax.validation.groups.Default;

import com.google.auto.common.MoreElements;
import org.gwtproject.validation.rebind.beaninfo.ConstraintDescriptor;
import org.gwtproject.validation.rebind.beaninfo.ConstraintValidator;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/20/19
 */
public class ConstraintDescriptorImpl implements ConstraintDescriptor {

    private AnnotationMirror annotation;

    private Map<String, Object> holder = new HashMap<>();

    private Map<String, DefaultValueHolder> defaultValues = new HashMap<>();

    public ConstraintDescriptorImpl(AnnotationMirror annotationMirror) {
        this.annotation = annotationMirror;
        for (ExecutableElement meth : ElementFilter.methodsIn(annotationMirror.getAnnotationType()
                                                                      .asElement()
                                                                      .getEnclosedElements())) {
            System.out.println("meth " + meth + " " + meth.getReturnType() + "  " + meth.getReturnType().toString().equals("java.lang.Class<?>[]"));

            AnnotationValue defaultValue = meth.getDefaultValue();
            if (defaultValue != null) {

                defaultValues.put(meth.getSimpleName().toString(), new DefaultValueHolder(meth.getReturnType().toString(),
                                                                                          parseValue(meth.getReturnType(), defaultValue.getValue())));
            }
        }

        annotationMirror.getElementValues().forEach((k, v) -> {
            this.holder.put(k.getSimpleName().toString(), parseValue(v.getValue()));
        });
    }

    //TODO shitty workarounds
    private static Object parseValue(Object value) {
        if (value.getClass().toString().contains("com.sun.tools.javac.util.List")) {
            List<String> result = new ArrayList<>();
            for (Object object : (java.util.AbstractCollection) value) {
                result.add(object.toString().replaceAll(".class", ""));
            }

/*            if (clazz.equals("com.sun.tools.javac.code.Attribute.Class")) {
                List<ClassWrapper> classes = new ArrayList<>();
                for (String s : result) {
                    classes.add(new ClassWrapper(s));
                }
                return classes.toArray(new ClassWrapper[classes.size()]);
            }*/
            return result.toArray();
        }
        return value;
    }

    private Object parseValue(TypeMirror typeMirror, Object value) {
        System.out.println("parseValue " + typeMirror.toString() + " " + value + " " + value.getClass());


        if (TypeKind.ARRAY.equals(typeMirror.getKind())) {
            ArrayType arrayType = (ArrayType) typeMirror;
            String componentType = arrayType.getComponentType().toString();
            List<Class> result = new ArrayList<>();
            if (componentType.startsWith("java.lang.Class")) {
                for (Class clazz : (java.util.AbstractCollection<Class<?>>) value) {
                    result.add(clazz);
                }
            }
            return result.toArray(new Class[result.size()]);
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
    public List<Class<? extends ConstraintValidator>> getConstraintValidatorClasses() {
        return null;
    }

    @Override
    public Map<String, DefaultValueHolder> getAttributes() {
        for (Map.Entry<String, Object> entry : holder.entrySet()) {
            if (defaultValues.containsKey(entry.getKey())) {
                defaultValues.get(entry.getKey()).value = entry.getValue();
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
            if (type.equals("java.lang.Class<?>[]")) {
//                this.value = "new java.lang.Class[] {}";
                this.value = value;

            } else {
                this.value = value;
            }
        }
    }
}

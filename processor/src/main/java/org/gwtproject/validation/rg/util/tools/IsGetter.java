package org.gwtproject.validation.rg.util.tools;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

public class IsGetter {

    public static boolean isGetterMethod(ExecutableElement method) {
        return isGetterName(method.getSimpleName().toString())
                && !hasParameters(method) && hasReturnValue(method);
    }

    private static boolean hasReturnValue(ExecutableElement method) {
        return method.getReturnType().getKind() != TypeKind.VOID;
    }

    private static boolean hasParameters(ExecutableElement method) {
        return !method.getParameters().isEmpty();
    }

    private static boolean isGetterName(String methodName) {
        return methodName.startsWith("is") || methodName.startsWith("has") || methodName.startsWith("get");
    }

    public static String getVariableName(ExecutableElement method) {
        if (isGetterMethod(method)) {
            String methodName = method.getSimpleName().toString();
            String variableName = null;
            if (methodName.startsWith("is") && methodName.length() > 2) {
                variableName = methodName.substring(2);
            } else if (methodName.length() > 3) {
                variableName = methodName.substring(3);
            }
            return variableName.substring(0, 1).toLowerCase() + variableName.substring(1);
        }
        return null;
    }
}

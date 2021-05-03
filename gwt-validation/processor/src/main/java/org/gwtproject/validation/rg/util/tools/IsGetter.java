/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.validation.rg.util.tools;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

public class IsGetter {

  public static boolean isGetterMethod(ExecutableElement method) {
    return isGetterName(method.getSimpleName().toString())
        && !hasParameters(method)
        && hasReturnValue(method);
  }

  private static boolean hasReturnValue(ExecutableElement method) {
    return method.getReturnType().getKind() != TypeKind.VOID;
  }

  private static boolean hasParameters(ExecutableElement method) {
    return !method.getParameters().isEmpty();
  }

  private static boolean isGetterName(String methodName) {
    return methodName.startsWith("is")
        || methodName.startsWith("has")
        || methodName.startsWith("get");
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

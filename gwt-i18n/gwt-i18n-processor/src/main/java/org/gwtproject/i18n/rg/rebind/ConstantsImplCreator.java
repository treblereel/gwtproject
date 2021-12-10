/*
 * Copyright 2008 Google Inc.
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
package org.gwtproject.i18n.rg.rebind;

import static org.gwtproject.i18n.rg.rebind.AbstractResource.*;

import com.google.auto.common.MoreTypes;
import java.util.Map;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Creates the class implementation for a given resource bundle using the standard <code>
 * AbstractGeneratorClassCreator</code>.
 */
class ConstantsImplCreator extends AbstractLocalizableImplCreator {
  /** Does a Map need to be generated in order to store complex results? */
  private boolean needCache = false;

  /**
   * Constructor for <code>ConstantsImplCreator</code>.
   *
   * @param logger logger to print errors
   * @param writer <code>Writer</code> to print to
   * @param localizableClass class/interface to conform to
   * @param resourceList resource bundle used to generate the class
   * @throws UnableToCompleteException
   */
  public ConstantsImplCreator(
      TreeLogger logger,
      AptContext context,
      SourceWriter writer,
      TypeElement localizableClass,
      ResourceList resourceList)
      throws UnableToCompleteException {
    super(logger, context, writer, localizableClass, resourceList, true);

    register(Type.STRING, new SimpleValueMethodCreator(this, SimpleValueMethodCreator.STRING));
    register(Type.STRING_MAP, new ConstantsMapMethodCreator(this));
    register(new Type(Map.class.getCanonicalName(), false), new ConstantsMapMethodCreator(this));
    register(Type.INT, new SimpleValueMethodCreator(this, SimpleValueMethodCreator.INT));
    register(Type.DOUBLE, new SimpleValueMethodCreator(this, SimpleValueMethodCreator.DOUBLE));
    register(Type.FLOAT, new SimpleValueMethodCreator(this, SimpleValueMethodCreator.FLOAT));
    register(Type.BOOLEAN, new SimpleValueMethodCreator(this, SimpleValueMethodCreator.BOOLEAN));
    register(
        new Type.ArrayType(String[].class.getCanonicalName(), Type.INT),
        new ConstantsStringArrayMethodCreator(this));
  }

  /**
   * Checks that the method has the right structure to implement <code>Constant</code>.
   *
   * @param method method to check
   */
  protected void checkConstantMethod(TreeLogger logger, ExecutableElement method)
      throws UnableToCompleteException {
    if (method.getParameters().size() > 0) {
      throw error(logger, "Methods in interfaces extending Constant must have no parameters");
    }
    checkReturnType(logger, method);
  }

  /**
   * @param logger
   * @param method
   * @throws UnableToCompleteException
   */
  protected void checkReturnType(TreeLogger logger, ExecutableElement method)
      throws UnableToCompleteException {
    TypeMirror returnType = method.getReturnType();
    if (returnType.getKind().isPrimitive()
        && (returnType.getKind().equals(TypeKind.BOOLEAN)
            || returnType.getKind().equals(TypeKind.DOUBLE)
            || returnType.getKind().equals(TypeKind.FLOAT)
            || returnType.getKind().equals(TypeKind.INT))) {
      return;
    }
    if (returnType.getKind().equals(TypeKind.ARRAY)) {
      String arrayComponent = MoreTypes.asArray(returnType).getComponentType().toString();
      if (!arrayComponent.equals("java.lang.String")) {
        throw error(
            logger, "Methods in interfaces extending Constant only support arrays of Strings");
      }
      return;
    }
    String returnTypeName = returnType.toString();
    if (returnTypeName.equals("java.lang.String")) {
      return;
    }
    if (returnTypeName.equals("java.util.Map")) {
      return;
    }
    if (returnTypeName.equals("java.util.Map<java.lang.String,java.lang.String>")) {
      return;
    }

    throw error(
        logger,
        "Methods in interfaces extending Constant must have a return type of "
            + "String/int/float/boolean/double/String[]/Map");
  }

  @Override
  protected void classEpilog() {
    if (isNeedCache()) {
      getWriter().println("java.util.Map cache = new java.util.HashMap();");
    }
  }

  /** Create the method body associated with the given method. Arguments are arg0...argN. */
  @Override
  protected void emitMethodBody(TreeLogger logger, ExecutableElement method, GwtLocale locale)
      throws UnableToCompleteException {
    checkConstantMethod(logger, method);
    delegateToCreator(logger, method, locale);
  }

  boolean isNeedCache() {
    return needCache;
  }

  void setNeedCache(boolean needCache) {
    this.needCache = needCache;
  }
}

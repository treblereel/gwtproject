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

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.server.Type;
import org.gwtproject.i18n.shared.GwtLocale;

/** Abstract functionality needed to create classes needed to supply generators. */
public abstract class AbstractGeneratorClassCreator extends AbstractSourceCreator {

  /**
   * Returns all interface methods associated with the given type.
   *
   * @param type associated type
   * @return interface methods.
   */
  public static ExecutableElement[] getAllInterfaceMethods(TypeElement type) {
    Map<String, ExecutableElement> methods = new LinkedHashMap<>();
    getAllInterfaceMethodsAux(type, methods);
    return methods.values().toArray(new ExecutableElement[methods.size()]);
  }

  private static void getAllInterfaceMethodsAux(
      TypeElement type, Map<String, ExecutableElement> m) {
    if (type.getKind().isInterface()) {
      for (Element elm : type.getEnclosedElements()) {
        if (elm.getKind().equals(ElementKind.METHOD)) {
          ExecutableElement method = MoreElements.asExecutable(elm);
          String s = uniqueMethodKey(method);
          if (m.get(s) == null) {
            m.put(s, method);
          }
        }
      }
      List<? extends TypeMirror> supers = type.getInterfaces();
      for (int i = 0; i < supers.size(); i++) {
        getAllInterfaceMethodsAux(MoreTypes.asTypeElement(supers.get(i)), m);
      }
    }
  }

  private static String uniqueMethodKey(ExecutableElement method) {
    String name = method.getSimpleName().toString();
    name += "(";
    List<? extends VariableElement> m = method.getParameters();
    for (int i = 0; i < m.size(); i++) {
      name += m.get(i).asType().toString() + " ";
    }
    name += ")";
    return name;
  }

  /**
   * List of registered method factories associated with <code>Constant</code> method
   * implementations.
   */
  protected Map<String, AbstractMethodCreator> methodFactories = new HashMap<>();

  /** The interface the generator is conforming to. */
  TypeElement targetClass;

  private final SourceWriter writer;

  /**
   * Creates a new class creator, supplies a place to write the class, the interface to conform to,
   * and the new name.
   *
   * @param writer writer
   * @param targetClass class name
   */
  public AbstractGeneratorClassCreator(SourceWriter writer, TypeElement targetClass) {
    this.targetClass = targetClass;
    this.writer = writer;
  }

  /**
   * Emits the new class.
   *
   * @param logger
   * @param locale
   * @throws UnableToCompleteException
   */
  public void emitClass(TreeLogger logger, GwtLocale locale) throws UnableToCompleteException {
    logger = branch(logger, branchMessage());
    classPrologue();
    emitMethods(logger, targetClass, locale);
    classEpilog();
    getWriter().outdent();
    writer.commit(logger);
  }

  public TypeElement getTarget() {
    return targetClass;
  }

  public UnableToCompleteException logMissingResource(
      TreeLogger logger, String during, MissingResourceException e) {
    String msg = "No resource found for key '" + e.getKey() + "'";
    if (during != null) {
      msg += " while " + during;
    }
    logger.log(TreeLogger.ERROR, msg, e);
    TreeLogger searchedBranch =
        logger.branch(TreeLogger.WARN, "Searched the following resources:", null);
    for (AbstractResource resource : e.getSearchedResources()) {
      TreeLogger resBranch = searchedBranch.branch(TreeLogger.WARN, resource.toString(), null);
      Set<String> keys = resource.keySet();
      TreeLogger keyBranch = resBranch.branch(TreeLogger.INFO, "List of keys found", null);
      if (keyBranch.isLoggable(TreeLogger.INFO)) {
        for (String key : keys) {
          keyBranch.log(TreeLogger.INFO, key, null);
        }
      }
    }
    return new UnableToCompleteException();
  }

  /**
   * Registers a method creator.
   *
   * @param returnType return type that this creator handles.
   * @param creator creator to register
   */
  public void register(Type returnType, AbstractMethodCreator creator) {
    // TODO: Hacked to get the gwt-trunk for 1.5 building.
    methodFactories.put(returnType.getSourceName(), creator);
  }

  /**
   * Returns the standard message when constructing a branch.
   *
   * @return branch message
   */
  protected String branchMessage() {
    return "Constructing " + targetClass;
  }

  /** Entry point for subclass cleanup code. */
  protected void classEpilog() {}

  /** Entry point for subclass setup code. */
  protected void classPrologue() {}

  /**
   * Emit method body, arguments are arg1...argN.
   *
   * @param logger TreeLogger for logging
   * @param method method to generate
   * @param locale locale for this generation
   * @throws UnableToCompleteException
   */
  protected abstract void emitMethodBody(
      TreeLogger logger, ExecutableElement method, GwtLocale locale)
      throws UnableToCompleteException;

  /**
   * Gets the method creator associated with the return type of the method.
   *
   * @param logger
   * @param method method to create
   * @return the method creator
   * @throws UnableToCompleteException
   */
  protected AbstractMethodCreator getMethodCreator(TreeLogger logger, ExecutableElement method)
      throws UnableToCompleteException {
    TypeMirror type = method.getReturnType();
    String simpleName = type.toString();
    AbstractMethodCreator methodCreator = methodFactories.get(simpleName);
    if (methodCreator == null) {
      String msg =
          "No current method creator exists for " + method + " only methods with return types of ";
      Iterator<String> iter = this.methodFactories.keySet().iterator();
      while (iter.hasNext()) {
        msg += iter.next();
        if (iter.hasNext()) {
          msg += ", ";
        }
      }
      msg += " can be created";
      throw error(logger, msg);
    }
    return methodCreator;
  }

  /**
   * Gets the associated writer.
   *
   * @return writer
   */
  protected SourceWriter getWriter() {
    return writer;
  }

  private void emitMethods(TreeLogger logger, TypeElement cur, GwtLocale locale)
      throws UnableToCompleteException {
    ExecutableElement[] x = getAllInterfaceMethods(cur);
    for (int i = 0; i < x.length; i++) {
      getWriter().println();
      genMethod(logger, x[i], locale);
    }
  }

  /**
   * Generates a method declaration for the given method.
   *
   * @param method method to generate
   * @param locale
   * @throws UnableToCompleteException
   */
  private void genMethod(TreeLogger logger, ExecutableElement method, GwtLocale locale)
      throws UnableToCompleteException {
    String name = method.getSimpleName().toString();
    String returnType = method.getReturnType().toString();
    getWriter().print("public " + returnType + " " + name + "(");
    List<? extends VariableElement> params = method.getParameters();
    for (int i = 0; i < params.size(); i++) {
      if (i != 0) {
        getWriter().print(",");
      }
      if (method.isVarArgs() && i == params.size() - 1) {
        ArrayType arrayType = MoreTypes.asArray(params.get(i).asType());
        getWriter().print(arrayType.getComponentType().toString() + "... arg" + (i));
      } else {
        getWriter().print(params.get(i).asType() + " arg" + (i));
      }
    }
    getWriter().println(") {");
    getWriter().indent();
    String methodName = method.getSimpleName().toString();
    TreeLogger branch = branch(logger, "Generating method body for " + methodName + "()");
    try {
      emitMethodBody(branch, method, locale);
    } catch (MissingResourceException e) {
      throw logMissingResource(branch, null, e);
    }
    getWriter().outdent();
    getWriter().println("}");
  }
}

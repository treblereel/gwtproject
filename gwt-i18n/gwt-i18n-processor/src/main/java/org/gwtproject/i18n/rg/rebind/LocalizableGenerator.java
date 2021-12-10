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

import com.google.auto.common.MoreElements;
import com.google.auto.common.MoreTypes;
import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.client.Constants;
import org.gwtproject.i18n.client.ConstantsWithLookup;
import org.gwtproject.i18n.client.LocaleInfo;
import org.gwtproject.i18n.client.Messages;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.PropertyOracle;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.Generator;
import org.gwtproject.i18n.server.CodeGenUtils;
import org.gwtproject.i18n.server.JavaSourceWriterBuilder;
import org.gwtproject.i18n.server.SourceWriter;
import org.gwtproject.i18n.shared.CustomDateTimeFormat;
import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Generator used to bind classes extending the <code>Localizable</code> and <code>Constants</code>
 * interfaces.
 */
public class LocalizableGenerator extends Generator {

  /**
   * Comparator for methods - sorts first by visibility, then name, then number of arguments, then
   * argument names.
   */
  public class ExecutableElementComparator implements Comparator<ExecutableElement> {

    public int compare(ExecutableElement a, ExecutableElement b) {
      if (a.getModifiers().contains(Modifier.PUBLIC)
          != b.getModifiers().contains(Modifier.PUBLIC)) {
        return a.getModifiers().contains(Modifier.PUBLIC) ? -1 : 1;
      }
      if (a.getModifiers().contains(Modifier.DEFAULT)
          != b.getModifiers().contains(Modifier.DEFAULT)) {
        return a.getModifiers().contains(Modifier.DEFAULT) ? -1 : 1;
      }
      if (a.getModifiers().contains(Modifier.PROTECTED)
          != b.getModifiers().contains(Modifier.PROTECTED)) {
        return a.getModifiers().contains(Modifier.PROTECTED) ? -1 : 1;
      }
      int c = a.getSimpleName().toString().compareTo(b.getSimpleName().toString());
      if (c != 0) {
        return c;
      }
      List<? extends VariableElement> aParams = a.getParameters();
      List<? extends VariableElement> bParams = b.getParameters();
      c = aParams.size() - bParams.size();
      if (c != 0) {
        return c;
      }
      for (int i = 0; i < aParams.size(); ++i) {
        c =
            aParams
                .get(i)
                .getSimpleName()
                .toString()
                .compareTo(bParams.get(i).getSimpleName().toString());
        if (c != 0) {
          return c;
        }
      }
      return 0;
    }
  }

  public static final String CONSTANTS_NAME = Constants.class.getCanonicalName();

  public static final String CONSTANTS_WITH_LOOKUP_NAME =
      ConstantsWithLookup.class.getCanonicalName();

  /**
   * Obsolete comment for GWT metadata - needs to be removed once all references have been removed.
   */
  public static final String GWT_KEY = "gwt.key";

  public static final String MESSAGES_NAME = Messages.class.getCanonicalName();

  private LocalizableLinkageCreator linkageCreator = new LocalizableLinkageCreator();

  /**
   * Generate an implementation for the given type.
   *
   * @param logger error logger
   * @param context generator context
   * @param typeName target type name
   * @return a fully-qualified classname of the generated implementation, or null to use the base
   *     class
   * @throws UnableToCompleteException
   */
  @Override
  public final String generate(TreeLogger logger, GeneratorContext context, String typeName)
      throws UnableToCompleteException {
    // Get the current locale
    PropertyOracle propertyOracle = context.getPropertyOracle();
    LocaleUtils localeUtils = LocaleUtils.getInstance(logger, propertyOracle, context);
    TypeElement targetClass = context.getAptContext().elements.getTypeElement(typeName);
    new ImplFactoryGenerator(logger, context, targetClass, localeUtils).generate();
    for (GwtLocale loc : localeUtils.getAllLocales()) {
      generate(logger, context, typeName, localeUtils, loc);
    }
    return generate(logger, context, typeName, localeUtils, localeUtils.getDefaultLocale());
  }

  /**
   * Generate an implementation for a given type.
   *
   * @param logger
   * @param context
   * @param typeName
   * @param localeUtils
   * @param locale
   * @return a fully-qualified classname of the generated implementation, or null to use the base
   *     class
   * @throws UnableToCompleteException
   */
  String generate(
      TreeLogger logger,
      GeneratorContext context,
      String typeName,
      LocaleUtils localeUtils,
      GwtLocale locale)
      throws UnableToCompleteException {
    TypeElement targetClass = context.getAptContext().elements.getTypeElement(typeName);
    if (targetClass == null) {
      logger.log(TreeLogger.ERROR, "No such type" + typeName);
      throw new UnableToCompleteException();
    }
    // Link current locale and interface type to correct implementation class.

    TypeElement constantsClass =
        context.getAptContext().elements.getTypeElement(LocalizableGenerator.CONSTANTS_NAME);
    TypeElement messagesClass =
        context.getAptContext().elements.getTypeElement(LocalizableGenerator.MESSAGES_NAME);
    TypeElement cdtfClass =
        context
            .getAptContext()
            .elements
            .getTypeElement(CustomDateTimeFormat.class.getCanonicalName());

    boolean assignableToConstants =
        context.getAptContext().types.isAssignable(targetClass.asType(), constantsClass.asType());
    boolean assignableToMessages =
        context.getAptContext().types.isAssignable(targetClass.asType(), messagesClass.asType());
    boolean assignableToCustomDateTimeFormatToMessages =
        context.getAptContext().types.isAssignable(targetClass.asType(), cdtfClass.asType());

    String generatedClass = null;

    if (assignableToConstants || assignableToMessages) {
      generatedClass =
          AbstractLocalizableImplCreator.generateConstantOrMessageClass(
              logger, context, locale, targetClass);
    } else if (assignableToCustomDateTimeFormatToMessages) {
      generatedClass = new CustomDateTimeFormatGenerator().generate(logger, context, typeName);
    }

    if (generatedClass != null) {
      return generatedClass;
    }

    // Now that we know it is a regular Localizable class, handle runtime
    // locales
    Set<GwtLocale> runtimeLocales = localeUtils.getRuntimeLocales();
    String returnedClass =
        linkageCreator.linkWithImplClass(logger, context.getAptContext(), targetClass, locale);
    if (!runtimeLocales.isEmpty()) {
      Map<String, Set<GwtLocale>> localeMap = new TreeMap<>();
      Set<GwtLocale> localeSet = new TreeSet<>();
      localeSet.add(locale);
      localeMap.put(returnedClass, localeSet);
      for (GwtLocale rtLocale : runtimeLocales) {
        String rtClass =
            linkageCreator.linkWithImplClass(
                logger, context.getAptContext(), targetClass, rtLocale);
        localeSet = localeMap.get(rtClass);
        if (localeSet == null) {
          localeSet = new TreeSet<>();
          localeMap.put(rtClass, localeSet);
        }
        localeSet.add(rtLocale);
      }
      if (localeMap.size() > 1) {
        CodeGenContext genCtx = new GwtCodeGenContext(logger, context);
        returnedClass =
            generateRuntimeSelection(genCtx, targetClass, returnedClass, locale, localeMap);
      }
    }
    return returnedClass;
  }

  /**
   * Generate a runtime-selection implementation of the target class if needed, delegating all
   * overridable methods to an instance chosen at runtime based on the map of locales to
   * implementing classes.
   *
   * @param ctx code generator context
   * @param targetClass class being GWT.create'd
   * @param defaultClass the default implementation to use
   * @param locale compile-time locale for this runtime selection
   * @param localeMap map of target class names to the set of locales that are mapped to that
   *     implementation (for deterministic code generation, both the map and set should be ordered)
   * @return fully qualified classname of the runtime selection implementation
   */
  @VisibleForTesting
  String generateRuntimeSelection(
      CodeGenContext ctx,
      TypeElement targetClass,
      String defaultClass,
      GwtLocale locale,
      Map<String, Set<GwtLocale>> localeMap) {
    String className =
        targetClass.getSimpleName().toString().replace('.', '_') + '_' + locale.getAsString();
    String pkgName = MoreElements.getPackage(targetClass).getQualifiedName().toString();
    JavaSourceWriterBuilder builder = ctx.addClass(pkgName, className);
    if (builder != null) {
      writeRuntimeSelection(builder, targetClass, defaultClass, locale, localeMap);
    }
    return pkgName + '.' + className;
  }

  /**
   * Generate a runtime-selection implementation of the target class, delegating all overridable
   * methods to an instance chosen at runtime based on the map of locales to implementing classes.
   *
   * @param builder source writer builder
   * @param targetClass class being GWT.create'd
   * @param defaultClass the default implementation to use
   * @param locale compile-time locale for this runtime selection
   * @param localeMap map of target class names to the set of locales that are mapped to that
   *     implementation (for deterministic code generation, both the map and set should be ordered)
   */
  @VisibleForTesting
  void writeRuntimeSelection(
      JavaSourceWriterBuilder builder,
      TypeElement targetClass,
      String defaultClass,
      GwtLocale locale,
      Map<String, Set<GwtLocale>> localeMap) {
    boolean isInterface = targetClass.getKind().isInterface();
    if (isInterface) {
      builder.addImplementedInterface(targetClass.getQualifiedName().toString());
    } else {
      builder.setSuperclass(targetClass.getQualifiedName().toString());
    }
    SourceWriter writer = builder.createSourceWriter();
    writer.println();
    writer.println("private " + targetClass.getQualifiedName().toString() + " instance;");
    for (ExecutableElement method : collectOverridableMethods(targetClass)) {
      writer.println();
      if (!isInterface) {
        writer.println("@Override");
      }
      writer.println(method.getSimpleName().toString() + " {");
      writer.indent();
      writer.println("ensureInstance();");
      if (!method.getReturnType().toString().equals(Void.class.getCanonicalName())) {
        writer.print("return ");
      }
      writer.print("instance." + method.getSimpleName().toString() + '(');
      boolean first = true;
      for (VariableElement param : method.getParameters()) {
        if (first) {
          first = false;
        } else {
          writer.print(", ");
        }
        writer.print(param.getSimpleName().toString());
      }
      writer.println(");");
      writer.outdent();
      writer.println("}");
    }
    writer.println();
    writer.println("private void ensureInstance() {");
    writer.indent();
    writer.println("if (instance != null) {");
    writer.indentln("return;");
    writer.println("}");
    writer.println(
        "String locale = "
            + LocaleInfo.class.getCanonicalName()
            + ".getCurrentLocale().getLocaleName();");
    String targetClassName = targetClass.getQualifiedName().toString() + '_' + locale.getAsString();
    for (Map.Entry<String, Set<GwtLocale>> entry : localeMap.entrySet()) {
      String implClassName = entry.getKey();
      if (defaultClass.equals(implClassName) || targetClassName.equals(implClassName)) {
        continue;
      }
      String prefix = "if (";
      for (GwtLocale match : entry.getValue()) {
        writer.print(prefix + CodeGenUtils.asStringLiteral(match.toString()) + ".equals(locale)");
        prefix = "\n    || ";
      }
      writer.println(") {");
      writer.indent();
      writer.println("instance = new " + implClassName + "();");
      writer.println("return;");
      writer.outdent();
      writer.println("}");
    }
    writer.println("instance = new " + defaultClass + "();");
    writer.outdent();
    writer.println("}");
    writer.close();
  }

  /**
   * @param targetClass
   * @return a set of overrideable methods, in the order they should appear in generated source
   */
  private TreeSet<ExecutableElement> collectOverridableMethods(TypeElement targetClass) {
    TreeSet<ExecutableElement> overrides = new TreeSet<>(new ExecutableElementComparator());
    Set<String> seenSignatures = new HashSet<>();
    // collect methods from superclass until we get to object
    for (TypeElement clazz = targetClass;
        clazz != null;
        clazz = MoreTypes.asTypeElement(clazz.getSuperclass())) {
      if ("java.lang.Object".equals(clazz.getQualifiedName().toString())) {
        break;
      }
      for (Element element : clazz.getEnclosedElements()) {
        if (element.getKind().equals(ElementKind.METHOD)) {
          ExecutableElement method = MoreElements.asExecutable(element);
          String signature = getSignature(method);
          if (!seenSignatures.contains(signature)) {
            seenSignatures.add(signature);
            if (!method.getModifiers().contains(Modifier.PRIVATE)
                && !method.getModifiers().contains(Modifier.FINAL)
                && !method.getModifiers().contains(Modifier.STATIC)) {
              overrides.add(method);
            }
          }
        }
      }
    }
    // collect methods from superinterfaces until hitting Localizable
    ArrayList<TypeElement> todo = new ArrayList<>();
    todo.addAll(
        targetClass.getInterfaces().stream()
            .map(i -> MoreTypes.asTypeElement(i))
            .collect(Collectors.toList()));
    while (!todo.isEmpty()) {
      TypeElement clazz = todo.remove(0);
      for (Element element : clazz.getEnclosedElements()) {
        if (element.getKind().equals(ElementKind.METHOD)) {
          ExecutableElement method = MoreElements.asExecutable(element);
          String signature = getSignature(method);
          if (!seenSignatures.contains(signature)) {
            seenSignatures.add(signature);
            if (!method.getModifiers().contains(Modifier.PRIVATE)
                && !method.getModifiers().contains(Modifier.FINAL)
                && !method.getModifiers().contains(Modifier.STATIC)) {
              overrides.add(method);
            }
          }
        }
      }
      if (!"Localizable".equals(clazz.getSimpleName().toString())) {
        todo.addAll(
            clazz.getInterfaces().stream()
                .map(i -> MoreTypes.asTypeElement(i))
                .collect(Collectors.toList()));
      }
    }
    return overrides;
  }

  /**
   * @param method
   * @return JNI signature of the method
   */
  private String getSignature(ExecutableElement method) {
    StringBuilder buf = new StringBuilder();
    buf.append(method.getSimpleName().toString()).append('(');
    for (VariableElement param : method.getParameters()) {
      TypeMirror type = param.asType();
      buf.append(type.toString());
    }
    return buf.append(')').toString();
  }
}

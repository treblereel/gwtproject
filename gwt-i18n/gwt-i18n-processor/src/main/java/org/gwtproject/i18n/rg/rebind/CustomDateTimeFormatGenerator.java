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
import java.io.PrintWriter;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import org.gwtproject.i18n.client.DateTimeFormat;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.PropertyOracle;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.Generator;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.rg.util.Util;
import org.gwtproject.i18n.shared.CustomDateTimeFormat;
import org.gwtproject.i18n.shared.GwtLocale;

/**
 * Generator used to generate an implementation of a {@link CustomDateTimeFormat} interface,
 * computing the best matching localized format patterns at compile time.
 */
public class CustomDateTimeFormatGenerator extends Generator {

  /**
   * Generate an implementation for the given type.
   *
   * @param logger error logger
   * @param context generator context
   * @param typeName target type name
   * @return generated class name
   * @throws UnableToCompleteException
   */
  @SuppressWarnings("deprecation")
  @Override
  public final String generate(TreeLogger logger, final GeneratorContext context, String typeName)
      throws UnableToCompleteException {
    PropertyOracle propertyOracle = context.getPropertyOracle();
    LocaleUtils localeUtils = LocaleUtils.getInstance(logger, propertyOracle, context);
    for (GwtLocale locale : localeUtils.getAllCompileLocales()) {
      generate(logger, context, typeName, locale);
    }
    generate(logger, context, typeName, localeUtils.getDefaultLocale());

    return typeName;
  }

  @SuppressWarnings("deprecation")
  private final String generate(
      TreeLogger logger, final GeneratorContext context, String typeName, GwtLocale locale)
      throws UnableToCompleteException {
    TypeElement targetClass = context.getAptContext().elements.getTypeElement(typeName);
    TypeElement oldDateTimeFormat =
        context
            .getAptContext()
            .elements
            .getTypeElement(org.gwtproject.i18n.shared.DateTimeFormat.class.getCanonicalName());
    TypeElement dateTimeFormat =
        context.getAptContext().elements.getTypeElement(DateTimeFormat.class.getCanonicalName());

    DateTimePatternGenerator dtpg = getDateTimePatternGenerator(locale);
    String packageName = MoreElements.getPackage(targetClass).getQualifiedName().toString();
    String className =
        Util.getJavaClassName(targetClass).replaceAll("\\.", "") + "_" + locale.getAsString();
    PrintWriter pw = context.tryCreate(logger, packageName, className);
    if (pw != null) {
      ClassSourceFileComposerFactory factory =
          new ClassSourceFileComposerFactory(packageName, className);
      factory.addImplementedInterface(targetClass.getQualifiedName().toString());
      factory.addImport("org.gwtproject.i18n.client.DateTimeFormat");
      SourceWriter writer = factory.createSourceWriter(context, pw);
      writer.indent();
      for (ExecutableElement method : Util.getMethods(targetClass)) {
        TypeMirror returnType = method.getReturnType();
        boolean sameNew =
            context.getAptContext().types.isSameType(returnType, dateTimeFormat.asType());
        boolean sameOld =
            context.getAptContext().types.isSameType(returnType, oldDateTimeFormat.asType());

        if (!sameNew && !sameOld) {
          logger.log(
              TreeLogger.ERROR,
              typeName + "." + method.getSimpleName().toString() + " must return DateTimeFormat");
          throw new UnableToCompleteException();
        }
        String pattern;
        org.gwtproject.i18n.shared.CustomDateTimeFormat.Pattern annotation =
            method.getAnnotation(org.gwtproject.i18n.shared.CustomDateTimeFormat.Pattern.class);
        if (annotation == null) {
          CustomDateTimeFormat.Pattern oldAnnotation =
              method.getAnnotation(CustomDateTimeFormat.Pattern.class);
          if (oldAnnotation == null) {
            logger.log(
                TreeLogger.ERROR,
                typeName + "." + method.getSimpleName() + " must have an @Pattern annotation");
            throw new UnableToCompleteException();
          }
          pattern = oldAnnotation.value();
        } else {
          pattern = annotation.value();
        }
        pattern = dtpg.getBestPattern(pattern);
        writer.println();
        String retTypeName = method.getReturnType().toString();
        writer.println("public " + retTypeName + " " + method.getSimpleName() + "() {");
        writer.println("  return " + retTypeName + ".getFormat(\"" + pattern + "\");");
        writer.println("}");
      }
      writer.commit(logger);
    }
    return packageName + "." + className;
  }

  protected DateTimePatternGenerator getDateTimePatternGenerator(GwtLocale gwtLocale) {
    return new DateTimePatternGenerator(gwtLocale);
  }
}

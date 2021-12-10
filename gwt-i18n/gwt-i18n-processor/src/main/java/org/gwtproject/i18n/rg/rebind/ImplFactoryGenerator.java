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
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.util.SourceWriter;
import org.gwtproject.i18n.rg.util.Util;
import org.gwtproject.i18n.server.CodeGenUtils;
import org.gwtproject.i18n.shared.GwtLocale;

/** @author Dmitrii Tikhomirov Created by treblereel 1/8/20 */
public class ImplFactoryGenerator {

  private final TreeLogger logger;
  private final GeneratorContext context;
  private final LocaleUtils localeUtils;
  private final String className;
  private final String classFactoryName;
  private final String packageName;

  ImplFactoryGenerator(
      TreeLogger logger,
      GeneratorContext context,
      TypeElement typeElement,
      LocaleUtils localeUtils) {
    this.logger = logger;
    this.context = context;
    this.localeUtils = localeUtils;
    this.className = Util.getJavaClassName(typeElement);
    this.classFactoryName = getFactoryClassName(typeElement);
    this.packageName = MoreElements.getPackage(typeElement).getQualifiedName().toString();
  }

  void generate() throws UnableToCompleteException {
    PrintWriter pw = context.tryCreate(logger, packageName, classFactoryName);
    if (pw != null) {
      ClassSourceFileComposerFactory factory =
          new ClassSourceFileComposerFactory(packageName, classFactoryName);
      factory.addImport("java.util.Map");
      factory.addImport("java.util.HashMap");
      SourceWriter writer = factory.createSourceWriter(context, pw);
      writer.println("private Map<String, " + className + "> holder = new HashMap<>();");
      writer.println();
      writer.println("private static " + classFactoryName + " instance;");
      writer.println();

      writer.println("private " + classFactoryName + "() {");
      localeUtils.getAllLocales().forEach(locale -> generateHolderEntry(writer, locale));
      generateHolderEntry(writer, localeUtils.getDefaultLocale());
      writer.println("}");
      writer.println();

      writer.println("public static " + className + " get() {");
      writer.println(
          "    String locale = org.gwtproject.i18n.client.LocaleInfo.getCurrentLocale().getLocaleName();");
      writer.println("    return " + classFactoryName + ".getFactory().getImpl(locale);");
      writer.println("}");
      writer.println();

      writer.println("private static " + classFactoryName + " getFactory() {");
      writer.println("    if(instance == null) {");
      writer.println("       instance = new " + classFactoryName + "();");
      writer.println("    }");
      writer.println("    return instance;");
      writer.println("}");
      writer.println();

      writer.println("public " + className + " getImpl(String locale) {");
      writer.println("    if(holder.containsKey(locale)) {");
      writer.println("        return holder.get(locale);");
      writer.println("    }");
      writer.println("    return holder.get(\"default\");");
      writer.println("}");
      writer.println();
      writer.commit(logger);
    }
  }

  private void generateHolderEntry(SourceWriter writer, GwtLocale locale) {
    writer.print("  holder.put(");
    writer.print(CodeGenUtils.asStringLiteral(locale.toString()));
    writer.print(", new " + className.replaceAll("\\.", "") + "_");
    writer.print(locale.toString().equals("default") ? "" : locale.toString());
    writer.print("());");
    writer.println();
  }

  private String getFactoryClassName(TypeElement typeElement) {
    return Util.getJavaClassName(typeElement).replaceAll("\\.", "") + "Factory";
  }
}

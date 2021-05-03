/*
 * Copyright © 2021 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gwtproject.i18n.apt;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.auto.service.AutoService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import org.apache.commons.io.IOUtils;
import org.gwtproject.i18n.shared.cldr.I18N;
import org.gwtproject.i18n.shared.cldr.impl.CurrencyList_;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"org.gwtproject.i18n.shared.cldr.I18N"})
public class ApplicationProcessor extends AbstractProcessor {

  private final String newLine = System.lineSeparator();

  private final List<String> processed = new ArrayList<>();

  @Override
  public boolean process(
      Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
    if (annotations.isEmpty()) {
      return false;
    }

    if (!roundEnvironment.getElementsAnnotatedWith(I18N.class).isEmpty()) {
      if (roundEnvironment.getElementsAnnotatedWith(I18N.class).size() > 1) {
        throw new Error("App must has only one class annotated with @Localizable");
      }
      String[] locales =
          roundEnvironment
              .getElementsAnnotatedWith(I18N.class)
              .iterator()
              .next()
              .getAnnotation(I18N.class)
              .value();

      Arrays.stream(locales).forEach(l -> System.out.println("LOCALE " + l));

      copy(processingEnv.getFiler(), locales);

      StringBuffer factory = new StringBuffer();
      factory
          .append("goog.module('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactoryImpl');")
          .append(newLine);
      factory.append(newLine);

      factory
          .append(
              "let LocaleInfoImpl = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.LocaleInfoImpl$impl');")
          .append(newLine);

      for (String locale : locales) {
        factory.append("let LocaleInfoImpl__");
        factory.append(locale);
        factory.append(
            " = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl_");
        factory.append(locale);
        factory.append("$impl');");
        factory.append(newLine);
      }

      factory.append(newLine);
      factory
          .append(
              "let LocaleInfoImpl__default = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl$impl');")
          .append(newLine);

      factory.append("class Locale {").append(newLine);
      factory.append(" /** @return {LocaleInfoImpl} */").append(newLine);
      factory.append(" static get(locale) {").append(newLine);
      factory.append("    switch(locale) {").append(newLine);

      for (String locale : locales) {
        factory.append("      case '");
        factory.append(locale);
        factory.append("':").append(newLine);

        factory.append("        return LocaleInfoImpl__");
        factory.append(locale);
        factory.append(".$create__();").append(newLine);
      }

      factory.append("      default:").append(newLine);
      factory.append("        return LocaleInfoImpl__default.$create__();").append(newLine);
      factory.append("    }").append(newLine);

      factory.append(" }").append(newLine);
      factory.append("}").append(newLine);

      factory.append(newLine);
      factory.append("exports = Locale;").append(newLine);

      writeResource(
          "org/gwtproject/i18n/shared/cldr/impl/LocaleInfoFactoryImpl.js",
          "org.gwtproject.i18n.shared.cldr.impl",
          factory.toString());
    }

    return true;
  }

  private void copy(Filer filer, String[] locales) {
    Set<String> fullLocaleList = new HashSet<>();
    fullLocaleList.addAll(Arrays.asList(locales));

    Class[] impls = {LocaleInfoImpl.class, CurrencyList_.class};

    Map<String, Map<String, URL>> holder = new HashMap<>();

    Arrays.stream(impls)
        .forEach(
            impl -> {
              holder.put(impl.getSimpleName(), new HashMap<>());
              for (String locale : locales) {

                System.out.println("P ? " + impl.getPackage().getName());

                URL url =
                    findResource(
                        filer,
                        impl.getPackage().getName(),
                        impl.getSimpleName().replaceAll("_", "") + "_" + locale + ".bak");
                holder.get(impl.getSimpleName()).put(locale, url);
                maybeAddSuper(impl, holder, url);
              }
            });

    for (String locale : locales) {
      URL url =
          findResource(
              filer, "org.gwtproject.i18n.shared.cldr.impl", "LocaleInfoImpl_" + locale + ".bak");

      InputStream in = null;
      try {
        in = url.openStream();
        String content = IOUtils.toString(in);
        CompilationUnit cu = StaticJavaParser.parse(content);

        new VoidVisitorAdapter<Object>() {
          @Override
          public void visit(ClassOrInterfaceDeclaration comment, Object arg) {
            super.visit(comment, arg);

            comment
                .getExtendedTypes()
                .forEach(
                    e -> {
                      System.out.println("Collected " + e.getName().asString());
                      if (!e.getName()
                          .asString()
                          .equals(LocaleInfoImpl.class.getCanonicalName())) {}
                    });
          }
        }.visit(cu, null);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(in);
      }
    }

    for (String locale : locales) {
      copy(
          filer,
          findResource(
              filer, "org.gwtproject.i18n.shared.cldr.impl", "LocaleInfoImpl_" + locale + ".bak"),
          "LocaleInfoImpl",
          locale);

      copy(
          filer,
          findResource(
              filer, "org.gwtproject.i18n.shared.cldr.impl", "CurrencyList_" + locale + ".bak"),
          "CurrencyList",
          locale);
      copy(
          filer,
          findResource(
              filer,
              "org.gwtproject.i18n.shared.cldr.impl",
              "DateTimeFormatInfoImpl_" + locale + ".bak"),
          "DateTimeFormatInfoImpl",
          locale);
      copy(
          filer,
          findResource(
              filer,
              "org.gwtproject.i18n.shared.cldr.impl",
              "LocalizedNamesImpl_" + locale + ".bak"),
          "LocalizedNamesImpl",
          locale);
      copy(
          filer,
          findResource(
              filer,
              "org.gwtproject.i18n.shared.cldr.impl",
              "NumberConstantsImpl_" + locale + ".bak"),
          "NumberConstantsImpl",
          locale);
    }
  }

  private void writeResource(String filename, String path, String content) {
    try {
      FileObject file =
          processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", filename);
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(file.openOutputStream(), "UTF-8"));
      pw.print(content);
      pw.close();
    } catch (IOException e) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to write file: " + e);
      throw new RuntimeException(e);
    }
  }

  public URL findResource(Filer filer, CharSequence pkg, CharSequence relativeName) {
    return findResource(
        filer,
        Arrays.asList(
            StandardLocation.SOURCE_PATH,
            StandardLocation.SOURCE_OUTPUT,
            StandardLocation.CLASS_PATH,
            StandardLocation.CLASS_OUTPUT,
            StandardLocation.ANNOTATION_PROCESSOR_PATH),
        pkg,
        relativeName);
  }

  private void maybeAddSuper(Class impl, Map<String, Map<String, URL>> holder, URL url) {

    InputStream in = null;
    try {
      in = url.openStream();
      String content = IOUtils.toString(in);
      CompilationUnit cu = StaticJavaParser.parse(content);

      new VoidVisitorAdapter<Object>() {
        @Override
        public void visit(ClassOrInterfaceDeclaration comment, Object arg) {
          super.visit(comment, arg);

          comment
              .getExtendedTypes()
              .forEach(
                  e -> {
                    System.out.println(
                        "Collected " + e.getName().asString() + " and " + impl.getSimpleName());
                    if (!e.getName().asString().equals(impl.getSimpleName())) {
                      System.out.println("ADD " + e);
                    }
                  });
        }
      }.visit(cu, null);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(in);
    }
  }

  private void copy(Filer filer, URL url, String className, String locale) {
    try {
      String clazz = "org.gwtproject.i18n.shared.cldr.impl." + className + "_" + locale;
      String source = IOUtils.toString(url);

      writeSourceFile(clazz, source, filer);
    } catch (FilerException e) {
    } catch (IOException e) {
      e.printStackTrace();
      throw new Error(e);
    }
  }

  private URL findResource(
      Filer filer,
      List<JavaFileManager.Location> searchLocations,
      CharSequence pkg,
      CharSequence relativeName) {
    for (JavaFileManager.Location location : searchLocations) {
      String path = "";
      if (pkg.length() > 0) {
        path = String.valueOf(pkg).replace('.', File.separatorChar);
      }
      try {
        FileObject fileObject = filer.getResource(location, "", path + relativeName);
        if (new File(fileObject.getName()).exists()) {
          return fileObject.toUri().toURL();
        } else {
          URL result = ApplicationProcessor.class.getResource("/" + path + "/" + relativeName);
          if (result != null) {
            return result;
          }
        }
      } catch (FilerException ignored) {
        File openedfile =
            new File(ignored.getMessage().replace("Attempt to reopen a file for path ", ""));
        if (openedfile.exists()) {
          try {
            return openedfile.toURI().toURL();
          } catch (MalformedURLException e) {
            // ignored
          }
        }
        // ignored
      } catch (IOException ignored) {
        // ignored
      }
    }
    throw new Error("Unable to find resource " + pkg + "." + relativeName);
  }

  private void writeSourceFile(String fileName, String source, Filer filer) throws IOException {
    if (processed.contains(fileName)) {
      return;
    }
    processed.add(fileName);
    JavaFileObject builderFile = filer.createSourceFile(fileName);
    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
      out.append(source);
    } catch (FilerException e) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to write class:");
      throw new RuntimeException(e);
    }
  }
}

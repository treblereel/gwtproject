/*
 * Copyright © 2018 The GWT Authors
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

import com.google.auto.service.AutoService;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"org.gwtproject.i18n.shared.cldr.I18N"})
public class ApplicationProcessor extends AbstractProcessor {

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
      try {
        if (Class.forName("com.google.gwt.core.client.GWT").getClass() != null) {
          throw new Error("EXIST!");
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }

      String[] locales =
          roundEnvironment
              .getElementsAnnotatedWith(I18N.class)
              .iterator()
              .next()
              .getAnnotation(I18N.class)
              .value();

      copy(processingEnv.getFiler(), locales);

      writeResource(
          "org/gwtproject/i18n/shared/cldr/impl/LocaleInfoFactoryImpl.js",
          "org.gwtproject.i18n.shared.cldr.impl",
          LocaleInfoFactoryGenerator.get(locales));

      writeResource(
          "org/gwtproject/i18n/shared/cldr/impl/CurrencyListFactoryImpl.js",
          "org.gwtproject.i18n.shared.cldr.impl",
          CurrencyListFactoryGenerator.get(locales));
    }

    return true;
  }

  private void copy(Filer filer, String[] locales) {
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

  private void copy(Filer filer, URL url, String className, String locale) {
    try {
      String clazz = "org.gwtproject.i18n.shared.cldr.impl." + className + "_" + locale;
      String source = IOUtils.toString(url);

      writeSourceFile(clazz, source, filer);
    } catch (IOException e) {
      e.printStackTrace();
      throw new Error(e);
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
    throw new Error("Unable to find resource " + relativeName);
  }
}

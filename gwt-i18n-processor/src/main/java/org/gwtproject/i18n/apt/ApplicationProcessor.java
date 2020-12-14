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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
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
        throw new Error("App must has only one class annotated with @I18N");
      }
      Generator generator = null;
      try {
        if (Class.forName("com.google.gwt.core.client.GWT").getClass() != null) {
          generator = new GWTApplicationProcessor(roundEnvironment, processingEnv);
        }
      } catch (ClassNotFoundException e) {
        generator = new J2CLApplicationProcessor(roundEnvironment, processingEnv);
      }
      generator.process();

    }

    return true;
  }
}

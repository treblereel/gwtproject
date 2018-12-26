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
package org.gwtproject.i18n.processor;

import com.google.auto.common.BasicAnnotationProcessor.ProcessingStep;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractProcessingStep implements ProcessingStep {

  protected final Messager messager;
  protected final Filer filer;
  protected final Types types;
  protected final Elements elements;
  protected final ProcessingEnvironment processingEnv;

  protected AbstractProcessingStep(ProcessingEnvironment processingEnv) {
    this.messager = processingEnv.getMessager();
    this.filer = processingEnv.getFiler();
    this.types = processingEnv.getTypeUtils();
    this.elements = processingEnv.getElementUtils();
    this.processingEnv = processingEnv;
  }

  /**
   *
   * @param e exception to be printed
   */
  public void printStackTrace(Exception e) {
    StringWriter out = new StringWriter();
    e.printStackTrace(new PrintWriter(out));
    messager.printMessage(Diagnostic.Kind.ERROR, "error while creating source file " + out.getBuffer().toString());
  }


}

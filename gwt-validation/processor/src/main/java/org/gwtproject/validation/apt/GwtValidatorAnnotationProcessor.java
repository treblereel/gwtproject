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
package org.gwtproject.validation.apt;

import com.google.auto.service.AutoService;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import org.gwtproject.validation.context.AptContext;
import org.gwtproject.validation.logger.PrintWriterTreeLogger;
import org.gwtproject.validation.rebind.ext.TreeLogger;
import org.gwtproject.validation.rebind.ext.UnableToCompleteException;

/** @author Dmitrii Tikhomirov Created by treblereel on 9/30/18. */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"org.gwtproject.validation.client.GwtValidation"})
public class GwtValidatorAnnotationProcessor extends AbstractProcessor {

  @Override
  public boolean process(
      Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
    if (annotations.isEmpty()) {
      return false;
    }
    AptContext context = new AptContext(processingEnv, roundEnvironment);
    TreeLogger logger = new PrintWriterTreeLogger();
    ((PrintWriterTreeLogger) logger).setMaxDetail(TreeLogger.Type.INFO);
    for (TypeElement annotation : annotations) {
      Set<TypeElement> elements =
          (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(annotation);
      try {
        new ClientBundleClassBuilder(logger, context, elements).process();
      } catch (UnableToCompleteException e) {
        throw new Error(e);
      }
    }
    return true;
  }
}

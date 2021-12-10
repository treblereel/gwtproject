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

package org.gwtproject.i18n.apt;

import com.google.auto.common.MoreElements;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.logger.PrintWriterTreeLogger;

/** @author Dmitrii Tikhomirov Created by treblereel 6/30/19 */
public class I18NAnnotationProcessor {

  private final Set<TypeElement> resources = new HashSet<>();

  private AptContext context;

  public boolean process(ProcessingEnvironment processingEnv, RoundEnvironment roundEnvironment) {

    context = new AptContext(processingEnv, roundEnvironment);
    TreeLogger logger = new PrintWriterTreeLogger();
    ((PrintWriterTreeLogger) logger).setMaxDetail(TreeLogger.Type.INFO);
    roundEnvironment.getElementsAnnotatedWith(org.gwtproject.i18n.client.Constant.class).stream()
        .map(element -> MoreElements.asType(element))
        .forEach(e -> resources.add(e));
    roundEnvironment.getElementsAnnotatedWith(org.gwtproject.i18n.client.Message.class).stream()
        .map(element -> MoreElements.asType(element))
        .forEach(e -> resources.add(e));
    try {
      new I18NBundleClassBuilder(logger, context, resources).process();
    } catch (UnableToCompleteException e) {
      throw new Error(e);
    }
    return true;
  }
}

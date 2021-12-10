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

import java.util.Set;
import javax.lang.model.element.TypeElement;
import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.StandardGeneratorContext;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.rebind.LocalizableGenerator;

/** @author Dmitrii Tikhomirov Created by treblereel 6/30/19 */
public class I18NBundleClassBuilder {
  private final TreeLogger logger;
  private final AptContext context;

  private final Set<TypeElement> elements;

  public I18NBundleClassBuilder(TreeLogger logger, AptContext context, Set<TypeElement> elements) {
    this.logger = logger;
    this.context = context;
    this.elements = elements;
  }

  public void process() throws UnableToCompleteException {
    StandardGeneratorContext standardGeneratorContext = new StandardGeneratorContext(context);
    LocalizableGenerator gen = new LocalizableGenerator();
    for (TypeElement element : elements) {
      gen.generate(logger, standardGeneratorContext, element.getQualifiedName().toString());
    }
  }
}

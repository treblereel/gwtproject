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

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;

/** @author Dmitrii Tikhomirov Created by treblereel 12/12/20 */
public class J2CLApplicationProcessor extends Generator {

  J2CLApplicationProcessor(RoundEnvironment roundEnvironment, ProcessingEnvironment processingEnv) {
    super(roundEnvironment, processingEnv);
  }

  @Override
  void process() {
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
}

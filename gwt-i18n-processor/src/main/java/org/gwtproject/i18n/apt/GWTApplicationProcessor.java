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

/**
 * @author Dmitrii Tikhomirov Created by treblereel 12/12/20
 */
public class GWTApplicationProcessor extends Generator {

    private final String[] templates = {"_DateTimeFormatInfo.bak", "_LocaleInfoImpl.bak", "_LocaleInfoImplFactory.bak", "_LocalizedNames.bak", "_NumberConstants.bak", "_CurrencyListFactory.bak","_CurrencyData.bak","_CurrencyList.bak"};

    GWTApplicationProcessor(RoundEnvironment roundEnvironment, ProcessingEnvironment processingEnv) {
        super(roundEnvironment, processingEnv);
    }

    @Override
    void process() {
        for (String template : templates) {
            String clazz = "org.gwtproject.i18n.shared.cldr.impl." + template.replace(".bak", "");
            copy(processingEnv.getFiler(), findResource(
                    processingEnv.getFiler(), "org.gwtproject.i18n.apt.template", template), clazz);
        }
    }
}

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

goog.module('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactoryImpl');

let LocaleInfoImpl = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.LocaleInfoImpl$impl');
let LocaleInfoImpl__en = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl_en$impl');

let LocaleInfoImpl__default = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl$impl');
class Locale {
 /** @return {LocaleInfoImpl} */
 static get(locale) {
    switch(locale) {
      case 'en':
        return LocaleInfoImpl__en.$create__();
      default:
        return LocaleInfoImpl__default.$create__();
    }
 }
}

exports = Locale;

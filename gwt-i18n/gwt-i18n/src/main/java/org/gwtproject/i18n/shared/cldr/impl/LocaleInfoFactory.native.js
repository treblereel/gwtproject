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

let localeInfoImpl = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl$impl');

org_gwtproject_i18n_shared_cldr_impl_LocaleInfoFactory.get = function(locale) {
  LocaleInfoFactory.$clinit();
  if (LocaleInfoFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_LocaleInfoFactory && LocaleInfoFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_LocaleInfoFactory.has(locale)) {
   return LocaleInfoFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_LocaleInfoFactory.get(locale);
  }
  localeInfoImpl = goog.module.get('org.gwtproject.i18n.shared.cldr.impl.LocaleInfoImpl$impl');
  return localeInfoImpl.$create__();
};

org_gwtproject_i18n_shared_cldr_impl_LocaleInfoFactory.m_getLocale__ = function() {
  return $Util.$getDefine("gwt.locale");
}

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

let currencyListImpl = goog.forwardDeclare('org.gwtproject.i18n.shared.cldr.impl.CurrencyList_$impl');

org_gwtproject_i18n_shared_cldr_impl_CurrencyListFactory.get = function(locale) {
  CurrencyListFactory.$clinit();
  if (CurrencyListFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_CurrencyListFactory && CurrencyListFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_CurrencyListFactory.has(locale)) {
   return CurrencyListFactory.$static_holder__org_gwtproject_i18n_shared_cldr_impl_CurrencyListFactory.get(locale);
  }
  currencyListImpl = goog.module.get('org.gwtproject.i18n.shared.cldr.impl.CurrencyList_$impl');
  return currencyListImpl.$create__();
};


org_gwtproject_i18n_shared_cldr_impl_CurrencyListFactory.m_getLocale__ = function() {
  return $Util.$getDefine("gwt.locale");
}
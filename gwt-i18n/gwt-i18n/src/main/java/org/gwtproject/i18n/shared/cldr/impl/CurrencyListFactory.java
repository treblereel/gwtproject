/*
 * Copyright © 2021 The GWT Project Authors
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

package org.gwtproject.i18n.shared.cldr.impl;

import elemental2.core.JsMap;
import javax.annotation.Generated;
import jsinterop.annotations.JsMethod;
import org.gwtproject.i18n.shared.cldr.CurrencyList;

@Generated(
    "gwt-cldr-importer : org.gwtproject.tools.cldr.CurrencyListProcessor, CLDR version : release-34")
public class CurrencyListFactory {

  public static final JsMap<String, CurrencyList> holder =
      new JsMap<String, org.gwtproject.i18n.shared.cldr.CurrencyList>();

  public static org.gwtproject.i18n.shared.cldr.CurrencyList create() {
    return get(getLocale());
  }

  @JsMethod
  @SuppressWarnings("unusable-by-js")
  public static native org.gwtproject.i18n.shared.cldr.CurrencyList get(String locale) /*-{
        var currencyList = @org.gwtproject.i18n.client.CurrencyList::get()();
        var currencyListDefault = currencyList.@org.gwtproject.i18n.client.CurrencyList::getDefault()();

        var getCurrencyCode = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::getCurrencyCode()();
        var getCurrencySymbol = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::getCurrencySymbol()();
        var getDefaultFractionDigits = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::getDefaultFractionDigits()();
        var getPortableCurrencySymbol = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::getPortableCurrencySymbol()();
        var getSimpleCurrencySymbol = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::getSimpleCurrencySymbol()();
        var isDeprecated = currencyListDefault.@org.gwtproject.i18n.client.CurrencyData::isDeprecated()();

        var bridgedCurrencyDataDefault = @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedCurrencyData::new(Ljava/lang/String;
                                                                                                        Ljava/lang/String;
                                                                                                        I
                                                                                                        Ljava/lang/String;
                                                                                                        Ljava/lang/String;
                                                                                                        Ljava/lang/Boolean;)
                                                                                                        (getCurrencyCode,
                                                                                                         getCurrencySymbol,
                                                                                                         getDefaultFractionDigits,
                                                                                                         getPortableCurrencySymbol,
                                                                                                         getSimpleCurrencySymbol,
                                                                                                         isDeprecated);


        var loadNamesMap = currencyList.@org.gwtproject.i18n.client.CurrencyList::loadNamesMapNative()();
        var loadCurrencyMapNative = currencyList.@org.gwtproject.i18n.client.CurrencyList::loadCurrencyMapNative()();
        var loadNamesHashMap = new Map();
        var loadCurrencyHashMap = new Map();

        for (var k in loadNamesMap) {
          if (loadNamesMap.hasOwnProperty(k)) {
            loadNamesHashMap.set(k,loadNamesMap[k]);
          }
        }

        for (var k in loadCurrencyMapNative) {
          if (loadCurrencyMapNative.hasOwnProperty(k)) {
            var arr = loadCurrencyMapNative[k];

            var getCurrencyCode = arr[0];
            var getCurrencySymbol = arr[1];
            var getDefaultFractionDigits = arr[2];
            var getPortableCurrencySymbol = arr[3];
            var getSimpleCurrencySymbol = arr[4];



            var currencyData = @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedCurrencyData::new(Ljava/lang/String;
                                                                                                            Ljava/lang/String;
                                                                                                            I
                                                                                                            Ljava/lang/String;
                                                                                                            Ljava/lang/String;)
                                                                                                            (getCurrencyCode,
                                                                                                             getCurrencySymbol,
                                                                                                             getDefaultFractionDigits,
                                                                                                             getPortableCurrencySymbol,
                                                                                                             getSimpleCurrencySymbol);
            loadCurrencyHashMap.set(k, currencyData);
          }
        }

        var result = @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedCurrencyList::new(Lorg/gwtproject/i18n/shared/cldr/CurrencyData;
                                                                                          Lelemental2/core/JsMap;
                                                                                          Lelemental2/core/JsMap;)
                                                                                          (bridgedCurrencyDataDefault, loadCurrencyHashMap, loadNamesHashMap);
      return result;
  }-*/;

  public static String getLocale() {
    return "default";
  }
}

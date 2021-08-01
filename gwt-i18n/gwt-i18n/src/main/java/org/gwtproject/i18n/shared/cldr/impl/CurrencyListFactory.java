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
import elemental2.dom.DomGlobal;
import javax.annotation.Generated;
import jsinterop.annotations.JsMethod;
import org.gwtproject.i18n.shared.cldr.CurrencyList;

@Generated(
    "gwt-cldr-importer : org.gwtproject.tools.cldr.CurrencyListProcessor, CLDR version : release-34")
public class CurrencyListFactory {

  public static final JsMap<String, CurrencyList> holder =
      new JsMap<String, org.gwtproject.i18n.shared.cldr.CurrencyList>();

  public static org.gwtproject.i18n.shared.cldr.CurrencyList create() {
    DomGlobal.console.log("CurrencyList create " + getLocale());
    return get(getLocale());
  }

  @JsMethod
  public static native org.gwtproject.i18n.shared.cldr.CurrencyList get(String locale) /*-{


  }-*/;

  public static String getLocale() {
    return "default";
  }
}

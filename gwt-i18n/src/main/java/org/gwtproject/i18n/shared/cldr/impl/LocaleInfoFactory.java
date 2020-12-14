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

package org.gwtproject.i18n.shared.cldr.impl;

import javax.annotation.Generated;
import jsinterop.annotations.JsMethod;

@Generated(
    "gwt-cldr-importer : org.gwtproject.tools.cldr.LocalesNativeNamesProcessor, CLDR version : release-34")
public class LocaleInfoFactory {

  public static String locale = System.getProperty("locale");

  public static org.gwtproject.i18n.shared.cldr.LocaleInfoImpl create() {
    return get(locale);
  }

  @JsMethod
  @SuppressWarnings("unusable-by-js")
  public static native org.gwtproject.i18n.shared.cldr.LocaleInfoImpl get(String locale)/*-{
    var rez = @org.gwtproject.i18n.shared.cldr.impl._LocaleInfoImplFactory::get(Ljava/lang/String;)(locale);
    return rez;
  }-*/;
}

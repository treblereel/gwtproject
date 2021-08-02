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

package org.gwtproject.i18n.shared.cldr.impl.gwt2;

import elemental2.core.JsMap;
import java.util.HashMap;
import org.gwtproject.i18n.shared.cldr.CurrencyData;
import org.gwtproject.i18n.shared.cldr.CurrencyList;

public class BridgedCurrencyList extends CurrencyList {

  private final CurrencyData getDefault;
  private final HashMap<String, CurrencyData> _loadCurrencyMap = new HashMap<>();
  private final HashMap<String, String> _loadNamesMap = new HashMap<>();

  BridgedCurrencyList(
      CurrencyData getDefault,
      JsMap<String, CurrencyData> loadCurrencyMap,
      JsMap<String, String> loadNamesMap) {
    this.getDefault = getDefault;

    loadNamesMap.forEach(
        (p0, p1, p2) -> {
          _loadNamesMap.put(p1, p0);
          return null;
        });

    loadCurrencyMap.forEach(
        (p0, p1, p2) -> {
          _loadCurrencyMap.put(p1, p0);
          return null;
        });
  }

  @Override
  public CurrencyData getDefault() {
    return getDefault;
  }

  @Override
  protected HashMap<String, CurrencyData> loadCurrencyMap() {
    return _loadCurrencyMap;
  }

  @Override
  protected HashMap<String, String> loadNamesMap() {
    return _loadNamesMap;
  }
}

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

import org.gwtproject.i18n.shared.cldr.CurrencyData;

public class BridgedCurrencyData implements CurrencyData {

  private final String currencyCode;

  private final String currencySymbol;
  private final int defaultFractionDigits;
  private final String portableCurrencySymbol;
  private final String simpleCurrencySymbol;
  private final Boolean isDeprecated;

  BridgedCurrencyData(
      String getCurrencyCode,
      String getCurrencySymbol,
      int getDefaultFractionDigits,
      String getPortableCurrencySymbol,
      String getSimpleCurrencySymbol) {
    this(
        getCurrencyCode,
        getCurrencySymbol,
        getDefaultFractionDigits,
        getPortableCurrencySymbol,
        getSimpleCurrencySymbol,
        false);
  }

  BridgedCurrencyData(
      String currencyCode,
      String currencySymbol,
      int defaultFractionDigits,
      String portableCurrencySymbol,
      String simpleCurrencySymbol,
      Boolean isDeprecated) {
    this.currencyCode = currencyCode;
    this.currencySymbol = currencySymbol;
    this.defaultFractionDigits = defaultFractionDigits;
    this.portableCurrencySymbol = portableCurrencySymbol;
    this.simpleCurrencySymbol = simpleCurrencySymbol;
    this.isDeprecated = isDeprecated;
  }

  @Override
  public String getCurrencyCode() {
    return currencyCode;
  }

  @Override
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  @Override
  public int getDefaultFractionDigits() {
    return defaultFractionDigits;
  }

  @Override
  public String getPortableCurrencySymbol() {
    return portableCurrencySymbol;
  }

  @Override
  public String getSimpleCurrencySymbol() {
    return simpleCurrencySymbol;
  }

  @Override
  public boolean isDeprecated() {
    return isDeprecated;
  }

  @Override
  public boolean isSpaceForced() {
    return false;
  }

  @Override
  public boolean isSpacingFixed() {
    return false;
  }

  @Override
  public boolean isSymbolPositionFixed() {
    return false;
  }

  @Override
  public boolean isSymbolPrefix() {
    return false;
  }

  @Override
  public String toString() {
    return "BridgedCurrencyData{"
        + "getCurrencyCode='"
        + currencyCode
        + '\''
        + ", getCurrencySymbol='"
        + currencySymbol
        + '\''
        + ", getDefaultFractionDigits="
        + defaultFractionDigits
        + ", getPortableCurrencySymbol='"
        + portableCurrencySymbol
        + '\''
        + ", getSimpleCurrencySymbol='"
        + simpleCurrencySymbol
        + '\''
        + ", isDeprecated="
        + isDeprecated
        + '}';
  }
}

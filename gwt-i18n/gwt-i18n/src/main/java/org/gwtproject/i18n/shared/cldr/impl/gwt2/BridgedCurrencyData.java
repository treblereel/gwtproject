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
import org.gwtproject.i18n.shared.cldr.CurrencyDataImpl;

public class BridgedCurrencyData implements CurrencyData {

  private final CurrencyDataImpl holder;
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

    holder =
        new CurrencyDataImpl(
            currencyCode,
            currencySymbol,
            defaultFractionDigits,
            portableCurrencySymbol,
            simpleCurrencySymbol);
    this.isDeprecated = isDeprecated;
  }

  @Override
  public String getCurrencyCode() {
    return holder.getCurrencyCode();
  }

  @Override
  public String getCurrencySymbol() {
    return holder.getCurrencySymbol();
  }

  @Override
  public int getDefaultFractionDigits() {
    return holder.getDefaultFractionDigits();
  }

  @Override
  public String getPortableCurrencySymbol() {
    return holder.getPortableCurrencySymbol();
  }

  @Override
  public String getSimpleCurrencySymbol() {
    return holder.getSimpleCurrencySymbol();
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
        + holder.getCurrencyCode()
        + '\''
        + ", getCurrencySymbol='"
        + holder.getCurrencySymbol()
        + '\''
        + ", getDefaultFractionDigits="
        + holder.getDefaultFractionDigits()
        + ", getPortableCurrencySymbol='"
        + holder.getPortableCurrencySymbol()
        + '\''
        + ", getSimpleCurrencySymbol='"
        + holder.getSimpleCurrencySymbol()
        + '\''
        + ", isDeprecated="
        + isDeprecated
        + '}';
  }
}

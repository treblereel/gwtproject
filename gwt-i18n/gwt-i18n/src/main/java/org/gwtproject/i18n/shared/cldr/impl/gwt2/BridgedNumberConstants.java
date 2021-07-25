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

import org.gwtproject.i18n.shared.cldr.NumberConstants;

class BridgedNumberConstants implements NumberConstants {

  private final String notANumber;
  private final String currencyPattern;
  private final String decimalPattern;
  private final String decimalSeparator;
  private final String defCurrencyCode;
  private final String exponentialSymbol;
  private final String globalCurrencyPattern;
  private final String groupingSeparator;
  private final String infinity;
  private final String minusSign;
  private final String monetaryGroupingSeparator;
  private final String monetarySeparator;
  private final String percent;
  private final String percentPattern;
  private final String perMill;
  private final String plusSign;
  private final String scientificPattern;
  private final String simpleCurrencyPattern;
  private final String zeroDigit;

  BridgedNumberConstants(
      String notANumber,
      String currencyPattern,
      String decimalPattern,
      String decimalSeparator,
      String defCurrencyCode,
      String exponentialSymbol,
      String globalCurrencyPattern,
      String groupingSeparator,
      String infinity,
      String minusSign,
      String monetaryGroupingSeparator,
      String monetarySeparator,
      String percent,
      String percentPattern,
      String perMill,
      String plusSign,
      String scientificPattern,
      String simpleCurrencyPattern,
      String zeroDigit) {
    this.notANumber = notANumber;
    this.currencyPattern = currencyPattern;
    this.decimalPattern = decimalPattern;
    this.decimalSeparator = decimalSeparator;
    this.defCurrencyCode = defCurrencyCode;
    this.exponentialSymbol = exponentialSymbol;
    this.globalCurrencyPattern = globalCurrencyPattern;
    this.groupingSeparator = groupingSeparator;
    this.infinity = infinity;
    this.minusSign = minusSign;
    this.monetaryGroupingSeparator = monetaryGroupingSeparator;
    this.monetarySeparator = monetarySeparator;
    this.percent = percent;
    this.percentPattern = percentPattern;
    this.perMill = perMill;
    this.plusSign = plusSign;
    this.scientificPattern = scientificPattern;
    this.simpleCurrencyPattern = simpleCurrencyPattern;
    this.zeroDigit = zeroDigit;
  }

  public String notANumber() {
    return notANumber;
  }

  public String currencyPattern() {
    return currencyPattern;
  }

  public String decimalPattern() {
    return decimalPattern;
  }

  public String decimalSeparator() {
    return decimalSeparator;
  }

  public String defCurrencyCode() {
    return defCurrencyCode;
  }

  public String exponentialSymbol() {
    return exponentialSymbol;
  }

  public String globalCurrencyPattern() {
    return globalCurrencyPattern;
  }

  public String groupingSeparator() {
    return groupingSeparator;
  }

  public String infinity() {
    return infinity;
  }

  public String minusSign() {
    return minusSign;
  }

  public String monetaryGroupingSeparator() {
    return monetaryGroupingSeparator;
  }

  public String monetarySeparator() {
    return monetarySeparator;
  }

  public String percent() {
    return percent;
  }

  public String percentPattern() {
    return percentPattern;
  }

  public String perMill() {
    return perMill;
  }

  public String plusSign() {
    return plusSign;
  }

  public String scientificPattern() {
    return scientificPattern;
  }

  public String simpleCurrencyPattern() {
    return simpleCurrencyPattern;
  }

  public String zeroDigit() {
    return zeroDigit;
  }
}

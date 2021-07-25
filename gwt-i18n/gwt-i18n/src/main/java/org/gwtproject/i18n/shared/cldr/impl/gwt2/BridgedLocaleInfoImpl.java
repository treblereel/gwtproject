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

import org.gwtproject.i18n.shared.cldr.DateTimeFormatInfo;
import org.gwtproject.i18n.shared.cldr.LocalizedNames;
import org.gwtproject.i18n.shared.cldr.NumberConstants;

class BridgedLocaleInfoImpl extends org.gwtproject.i18n.shared.cldr.LocaleInfoImpl {

  private final String name;
  private final Boolean isRTL;
  private final DateTimeFormatInfo dateTimeFormatInfo;
  private final BridgedLocalizedNamesImplBase localizedNames;
  private final BridgedNumberConstants bridgedNumberConstants;

  public BridgedLocaleInfoImpl(
      String name,
      Boolean isRTL,
      BridgedDateTimeFormatInfo dateTimeFormatInfo,
      BridgedLocalizedNamesImplBase localizedNames,
      BridgedNumberConstants bridgedNumberConstants) {
    this.name = name;
    this.isRTL = isRTL;
    this.dateTimeFormatInfo = dateTimeFormatInfo;
    this.localizedNames = localizedNames;
    this.bridgedNumberConstants = bridgedNumberConstants;
  }

  @Override
  public String getLocaleName() {
    return name;
  }

  @Override
  public boolean isRTL() {
    return isRTL;
  }

  public DateTimeFormatInfo getDateTimeFormatInfo() {
    return dateTimeFormatInfo;
  }

  public LocalizedNames getLocalizedNames() {
    return localizedNames;
  }

  public NumberConstants getNumberConstants() {
    return bridgedNumberConstants;
  }
}

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
import org.gwtproject.i18n.shared.cldr.DateTimeFormatInfo;
import org.gwtproject.i18n.shared.cldr.DateTimeFormatInfoImpl;
import org.gwtproject.i18n.shared.cldr.LocalizedNames;
import org.gwtproject.i18n.shared.cldr.NumberConstants;

@Generated(
    "gwt-cldr-importer : org.gwtproject.tools.cldr.LocalesNativeNamesProcessor, CLDR version : release-34")
public class LocaleInfoImpl extends org.gwtproject.i18n.shared.cldr.LocaleInfoImpl {
  @Override
  public String getLocaleName() {
    return "default";
  }

  @Override
  public LocalizedNames getLocalizedNames() {
    return new LocalizedNamesImpl();
  }

  @Override
  public DateTimeFormatInfo getDateTimeFormatInfo() {
    return new DateTimeFormatInfoImpl();
  }

  @Override
  public NumberConstants getNumberConstants() {
    return new NumberConstantsImpl();
  }

  @Override
  public boolean isRTL() {
    return false;
  }
}

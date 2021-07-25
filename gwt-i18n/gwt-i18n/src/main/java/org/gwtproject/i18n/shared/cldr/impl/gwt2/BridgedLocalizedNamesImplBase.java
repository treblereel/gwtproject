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

import org.gwtproject.i18n.shared.cldr.LocalizedNames;

class BridgedLocalizedNamesImplBase implements LocalizedNames {

  private final Functions.CallbackRtrnStringArray getLikelyRegionCodes;
  private final Functions.CallbackRtrnString getRegionName;
  private final Functions.CallbackRtrnStringArray getSortedRegionCodes;

  BridgedLocalizedNamesImplBase(
      Functions.CallbackRtrnStringArray getLikelyRegionCodes,
      Functions.CallbackRtrnString getRegionName,
      Functions.CallbackRtrnStringArray getSortedRegionCodes) {
    this.getLikelyRegionCodes = getLikelyRegionCodes;
    this.getRegionName = getRegionName;
    this.getSortedRegionCodes = getSortedRegionCodes;
  }

  public String[] getLikelyRegionCodes() {
    return getLikelyRegionCodes.onCallback();
  }

  public String getRegionName(String s) {
    return getRegionName.onCallback(s);
  }

  public String[] getSortedRegionCodes() {
    return getSortedRegionCodes.onCallback();
  }
}

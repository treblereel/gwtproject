/*
 * Copyright © 2021 The GWT Authors
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
import elemental2.dom.URLSearchParams;
import jsinterop.annotations.JsMethod;

public class LocaleInfoFactory {

  public static final JsMap<String, org.gwtproject.i18n.shared.cldr.LocaleInfoImpl> holder =
      new JsMap<String, org.gwtproject.i18n.shared.cldr.LocaleInfoImpl>();

  private static String current;

  static {
    String queryString = DomGlobal.window.location.search;
    URLSearchParams params = new URLSearchParams(queryString);
    if (params.has("locale")) {
      current = params.get("locale");
    } else {
      current = getLocale();
    }
  }

  public static org.gwtproject.i18n.shared.cldr.LocaleInfoImpl create() {
    return get(current);
  }

  @JsMethod
  @SuppressWarnings("unusable-by-js")
  public static native org.gwtproject.i18n.shared.cldr.LocaleInfoImpl get(String locale) /*-{

      var getCurrentLocale = @org.gwtproject.i18n.client.LocaleInfo::getCurrentLocale()();
      var isRTL = getCurrentLocale.@org.gwtproject.i18n.client.LocaleInfo::isRTL()();
      var name = getCurrentLocale.@org.gwtproject.i18n.client.LocaleInfo::getLocaleName()();


        var dateTimeFormatInfo = getCurrentLocale.@org.gwtproject.i18n.client.LocaleInfo::getDateTimeFormatInfo()();

        var ampms = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::ampms()();
        var dateFormat = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateFormat()();
        var dateFormatFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateFormatFull()();
        var dateFormatLong = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateFormatLong()();
        var dateFormatMedium = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateFormatMedium()();
        var dateFormatShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateFormatShort()();

        var dateTime = function(obj1, obj2) {
            return dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateTime(Ljava/lang/String;Ljava/lang/String;)(obj1,obj2);
        }
        var dateTimeFull = function(obj1, obj2) {
            return dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateTimeFull(Ljava/lang/String;Ljava/lang/String;)(obj1,obj2);
        }
        var dateTimeLong = function(obj1, obj2) {
            return dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateTimeLong(Ljava/lang/String;Ljava/lang/String;)(obj1,obj2);
        }
        var dateTimeMedium = function(obj1, obj2) {
            return dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateTimeMedium(Ljava/lang/String;Ljava/lang/String;)(obj1,obj2);
        }
        var dateTimeShort = function(obj1, obj2) {
            return dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::dateTimeShort(Ljava/lang/String;Ljava/lang/String;)(obj1,obj2);
        }

        var erasFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::erasFull()();
        var erasShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::erasShort()();
        var firstDayOfTheWeek = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::firstDayOfTheWeek()();
        var formatDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatDay()();
        var formatHour12Minute = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatHour12Minute()();
        var formatHour12MinuteSecond = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatHour12MinuteSecond()();
        var formatHour24Minute = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatHour24Minute()();
        var formatHour24MinuteSecond = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatHour24MinuteSecond()();
        var formatMinuteSecond = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMinuteSecond()();
        var formatMonthAbbrev = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthAbbrev()();
        var formatMonthAbbrevDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthAbbrevDay()();
        var formatMonthFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthFull()();
        var formatMonthFullDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthFullDay()();
        var formatMonthFullWeekdayDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthFullWeekdayDay()();
        var formatMonthNumDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatMonthNumDay()();
        var formatYear = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYear()();
        var formatYearMonthAbbrev = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthAbbrev()();
        var formatYearMonthAbbrevDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthAbbrevDay()();
        var formatYearMonthFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthFull()();
        var formatYearMonthFullDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthFullDay()();
        var formatYearMonthNum = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthNum()();
        var formatYearMonthNumDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthNumDay()();
        var formatYearMonthWeekdayDay = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearMonthWeekdayDay()();
        var formatYearQuarterFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearQuarterFull()();
        var formatYearQuarterShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::formatYearQuarterShort()();

        var monthsFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsFull()();
        var monthsFullStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsFullStandalone()();
        var monthsNarrow = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsNarrow()();
        var monthsNarrowStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsNarrowStandalone()();
        var monthsShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsShort()();
        var monthsShortStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::monthsShortStandalone()();
        var quartersFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::quartersFull()();
        var quartersShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::quartersShort()();

        var timeFormat = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::timeFormat()();
        var timeFormatFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::timeFormatFull()();
        var timeFormatLong = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::timeFormatLong()();
        var timeFormatMedium = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::timeFormatMedium()();
        var timeFormatShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::timeFormatShort()();

        var weekdaysFull = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysFull()();
        var weekdaysFullStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysFullStandalone()();
        var weekdaysNarrow = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysNarrow()();
        var weekdaysNarrowStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysNarrowStandalone()();
        var weekdaysShort = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysShort()();
        var weekdaysShortStandalone = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekdaysShortStandalone()();

        var weekendEnd = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekendEnd()();
        var weekendStart = dateTimeFormatInfo.@org.gwtproject.i18n.shared.DateTimeFormatInfo::weekendStart()();


        var newDateTimeFormatInfo = @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedDateTimeFormatInfo::new([Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$Callback;
                                                                                                  Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$Callback;
                                                                                                  Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$Callback;
                                                                                                  Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$Callback;
                                                                                                  Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$Callback;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  I
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;

                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;

                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;
                                                                                                  Ljava/lang/String;

                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;
                                                                                                  [Ljava/lang/String;

                                                                                                  I
                                                                                                  I)(
                                                                                                   ampms,
                                                                                                   dateFormat,
                                                                                                   dateFormatFull,
                                                                                                   dateFormatLong,
                                                                                                   dateFormatMedium,
                                                                                                   dateFormatShort,
                                                                                                   dateTime,
                                                                                                   dateTimeFull,
                                                                                                   dateTimeLong,
                                                                                                   dateTimeMedium,
                                                                                                   dateTimeShort,
                                                                                                   erasFull,
                                                                                                   erasShort,
                                                                                                   firstDayOfTheWeek,
                                                                                                   formatDay,
                                                                                                   formatHour12Minute,
                                                                                                   formatHour12MinuteSecond,
                                                                                                   formatHour24Minute,
                                                                                                   formatHour24MinuteSecond,
                                                                                                   formatMinuteSecond,
                                                                                                   formatMonthAbbrev,
                                                                                                   formatMonthAbbrevDay,
                                                                                                   formatMonthFull,
                                                                                                   formatMonthFullDay,
                                                                                                   formatMonthFullWeekdayDay,
                                                                                                   formatMonthNumDay,
                                                                                                   formatYear,
                                                                                                   formatYearMonthAbbrev,
                                                                                                   formatYearMonthAbbrevDay,
                                                                                                   formatYearMonthFull,
                                                                                                   formatYearMonthFullDay,
                                                                                                   formatYearMonthNum,
                                                                                                   formatYearMonthNumDay,
                                                                                                   formatYearMonthWeekdayDay,
                                                                                                   formatYearQuarterFull,
                                                                                                   formatYearQuarterShort,

                                                                                                   monthsFull,
                                                                                                   monthsFullStandalone,
                                                                                                   monthsNarrow,
                                                                                                   monthsNarrowStandalone,
                                                                                                   monthsShort,
                                                                                                   monthsShortStandalone,
                                                                                                   quartersFull,
                                                                                                   quartersShort,

                                                                                                   timeFormat,
                                                                                                   timeFormatFull,
                                                                                                   timeFormatLong,
                                                                                                   timeFormatMedium,
                                                                                                   timeFormatShort,

                                                                                                   weekdaysFull,
                                                                                                   weekdaysFullStandalone,
                                                                                                   weekdaysNarrow,
                                                                                                   weekdaysNarrowStandalone,
                                                                                                   weekdaysShort,
                                                                                                   weekdaysShortStandalone,

                                                                                                   weekendEnd,
                                                                                                   weekendStart

                                                                                                   );

        var localizedNames = getCurrentLocale.@org.gwtproject.i18n.client.LocaleInfo::getLocalizedNames()();

        var getLikelyRegionCodes = function() {
            return localizedNames.@org.gwtproject.i18n.shared.cldr.LocalizedNames::getLikelyRegionCodes()();
        }

        var getRegionName = function(arg) {
            return localizedNames.@org.gwtproject.i18n.shared.cldr.LocalizedNames::getRegionName(Ljava/lang/String;)(arg);
        }

        var getSortedRegionCodes = function() {
            return localizedNames.@org.gwtproject.i18n.shared.cldr.LocalizedNames::getSortedRegionCodes()();
        }

        var bridgedLocalizedNamesImplBase = @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedLocalizedNamesImplBase::new(Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$CallbackRtrnStringArray;
                                                                                                           Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$CallbackRtrnString;
                                                                                                           Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/Functions$CallbackRtrnStringArray;)
                                                                                                           (getLikelyRegionCodes, getRegionName, getSortedRegionCodes);

        var numberConstants = getCurrentLocale.@org.gwtproject.i18n.client.LocaleInfo::getNumberConstants()();

        var notANumber = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::notANumber()();
        var currencyPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::currencyPattern()();
        var decimalPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::decimalPattern()();
        var decimalSeparator = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::decimalSeparator()();
        var defCurrencyCode = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::defCurrencyCode()();
        var exponentialSymbol = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::exponentialSymbol()();
        var globalCurrencyPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::globalCurrencyPattern()();
        var groupingSeparator = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::groupingSeparator()();
        var infinity = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::infinity()();
        var minusSign = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::minusSign()();
        var monetaryGroupingSeparator = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::monetaryGroupingSeparator()();
        var monetarySeparator = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::monetarySeparator()();
        var percent = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::percent()();
        var percentPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::percentPattern()();
        var perMill = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::perMill()();
        var plusSign = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::plusSign()();
        var scientificPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::scientificPattern()();
        var simpleCurrencyPattern = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::simpleCurrencyPattern()();
        var zeroDigit = numberConstants.@org.gwtproject.i18n.client.constants.NumberConstants::zeroDigit()();

        var bridgedNumberConstants = new @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedNumberConstants::new(Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   Ljava/lang/String;
                                                                                                   )(notANumber,
                                                                                                   currencyPattern,
                                                                                                   decimalPattern,
                                                                                                   decimalSeparator,
                                                                                                   defCurrencyCode,
                                                                                                   exponentialSymbol,
                                                                                                   globalCurrencyPattern,
                                                                                                   groupingSeparator,
                                                                                                   infinity,
                                                                                                   minusSign,
                                                                                                   monetaryGroupingSeparator,
                                                                                                   monetarySeparator,
                                                                                                   percent,
                                                                                                   percentPattern,
                                                                                                   perMill,
                                                                                                   plusSign,
                                                                                                   scientificPattern,
                                                                                                   simpleCurrencyPattern,
                                                                                                   zeroDigit);


        var impl = new @org.gwtproject.i18n.shared.cldr.impl.gwt2.BridgedLocaleInfoImpl::new(Ljava/lang/String;
                                                                              Ljava/lang/Boolean;
                                                                              Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/BridgedDateTimeFormatInfo;
                                                                              Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/BridgedLocalizedNamesImplBase;
                                                                              Lorg/gwtproject/i18n/shared/cldr/impl/gwt2/BridgedNumberConstants;
                                                                              )
                                                                              (name, isRTL, newDateTimeFormatInfo, bridgedLocalizedNamesImplBase, bridgedNumberConstants);



    return impl;
  }-*/;

  public static String getLocale() {
    return "default";
  }
}

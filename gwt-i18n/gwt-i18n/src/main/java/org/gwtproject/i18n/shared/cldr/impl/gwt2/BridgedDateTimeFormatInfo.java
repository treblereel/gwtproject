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

class BridgedDateTimeFormatInfo implements DateTimeFormatInfo {

  private final String[] ampms;
  private final String dateFormat;
  private final String dateFormatFull;
  private final String dateFormatLong;
  private final String dateFormatMedium;
  private final String dateFormatShort;
  private final Functions.Callback dateTime;
  private final Functions.Callback dateTimeFull;
  private final Functions.Callback dateTimeLong;
  private final Functions.Callback dateTimeMedium;
  private final Functions.Callback dateTimeShort;
  private final String[] erasFull;
  private final String[] erasShort;
  private final int firstDayOfTheWeek;
  private final String formatDay;
  private final String formatHour12Minute;
  private final String formatHour12MinuteSecond;
  private final String formatHour24Minute;
  private final String formatHour24MinuteSecond;
  private final String formatMinuteSecond;
  private final String formatMonthAbbrev;
  private final String formatMonthAbbrevDay;
  private final String formatMonthFull;
  private final String formatMonthFullDay;
  private final String formatMonthFullWeekdayDay;
  private final String formatMonthNumDay;
  private final String formatYear;
  private final String formatYearMonthAbbrev;
  private final String formatYearMonthAbbrevDay;
  private final String formatYearMonthFull;
  private final String formatYearMonthFullDay;
  private final String formatYearMonthNum;
  private final String formatYearMonthNumDay;
  private final String formatYearMonthWeekdayDay;
  private final String formatYearQuarterFull;
  private final String formatYearQuarterShort;
  private final String[] monthsFull;
  private final String[] monthsFullStandalone;
  private final String[] monthsNarrow;
  private final String[] monthsNarrowStandalone;
  private final String[] monthsShort;
  private final String[] monthsShortStandalone;
  private final String[] quartersFull;
  private final String[] quartersShort;
  private final String timeFormat;
  private final String timeFormatFull;
  private final String timeFormatLong;
  private final String timeFormatMedium;
  private final String timeFormatShort;
  private final String[] weekdaysFull;
  private final String[] weekdaysFullStandalone;
  private final String[] weekdaysNarrow;
  private final String[] weekdaysNarrowStandalone;
  private final String[] weekdaysShort;
  private final String[] weekdaysShortStandalone;
  private final int weekendEnd;
  private final int weekendStart;

  public BridgedDateTimeFormatInfo(
      String[] ampms,
      String dateFormat,
      String dateFormatFull,
      String dateFormatLong,
      String dateFormatMedium,
      String dateFormatShort,
      Functions.Callback dateTime,
      Functions.Callback dateTimeFull,
      Functions.Callback dateTimeLong,
      Functions.Callback dateTimeMedium,
      Functions.Callback dateTimeShort,
      String[] erasFull,
      String[] erasShort,
      int firstDayOfTheWeek,
      String formatDay,
      String formatHour12Minute,
      String formatHour12MinuteSecond,
      String formatHour24Minute,
      String formatHour24MinuteSecond,
      String formatMinuteSecond,
      String formatMonthAbbrev,
      String formatMonthAbbrevDay,
      String formatMonthFull,
      String formatMonthFullDay,
      String formatMonthFullWeekdayDay,
      String formatMonthNumDay,
      String formatYear,
      String formatYearMonthAbbrev,
      String formatYearMonthAbbrevDay,
      String formatYearMonthFull,
      String formatYearMonthFullDay,
      String formatYearMonthNum,
      String formatYearMonthNumDay,
      String formatYearMonthWeekdayDay,
      String formatYearQuarterFull,
      String formatYearQuarterShort,
      String[] monthsFull,
      String[] monthsFullStandalone,
      String[] monthsNarrow,
      String[] monthsNarrowStandalone,
      String[] monthsShort,
      String[] monthsShortStandalone,
      String[] quartersFull,
      String[] quartersShort,
      String timeFormat,
      String timeFormatFull,
      String timeFormatLong,
      String timeFormatMedium,
      String timeFormatShort,
      String[] weekdaysFull,
      String[] weekdaysFullStandalone,
      String[] weekdaysNarrow,
      String[] weekdaysNarrowStandalone,
      String[] weekdaysShort,
      String[] weekdaysShortStandalone,
      int weekendEnd,
      int weekendStart) {
    this.ampms = ampms;
    this.dateFormat = dateFormat;
    this.dateFormatFull = dateFormatFull;
    this.dateFormatLong = dateFormatLong;
    this.dateFormatMedium = dateFormatMedium;
    this.dateFormatShort = dateFormatShort;
    this.dateTime = dateTime;
    this.dateTimeFull = dateTimeFull;
    this.dateTimeLong = dateTimeLong;
    this.dateTimeMedium = dateTimeMedium;
    this.dateTimeShort = dateTimeShort;
    this.erasFull = erasFull;
    this.erasShort = erasShort;
    this.firstDayOfTheWeek = firstDayOfTheWeek;
    this.formatDay = formatDay;
    this.formatHour12Minute = formatHour12Minute;
    this.formatHour12MinuteSecond = formatHour12MinuteSecond;
    this.formatHour24Minute = formatHour24Minute;
    this.formatHour24MinuteSecond = formatHour24MinuteSecond;
    this.formatMinuteSecond = formatMinuteSecond;
    this.formatMonthAbbrev = formatMonthAbbrev;
    this.formatMonthAbbrevDay = formatMonthAbbrevDay;
    this.formatMonthFull = formatMonthFull;
    this.formatMonthFullDay = formatMonthFullDay;
    this.formatMonthFullWeekdayDay = formatMonthFullWeekdayDay;
    this.formatMonthNumDay = formatMonthNumDay;
    this.formatYear = formatYear;
    this.formatYearMonthAbbrev = formatYearMonthAbbrev;
    this.formatYearMonthAbbrevDay = formatYearMonthAbbrevDay;
    this.formatYearMonthFull = formatYearMonthFull;
    this.formatYearMonthFullDay = formatYearMonthFullDay;
    this.formatYearMonthNum = formatYearMonthNum;
    this.formatYearMonthNumDay = formatYearMonthNumDay;
    this.formatYearMonthWeekdayDay = formatYearMonthWeekdayDay;
    this.formatYearQuarterFull = formatYearQuarterFull;
    this.formatYearQuarterShort = formatYearQuarterShort;
    this.monthsFull = monthsFull;
    this.monthsFullStandalone = monthsFullStandalone;
    this.monthsNarrow = monthsNarrow;
    this.monthsNarrowStandalone = monthsNarrowStandalone;
    this.monthsShort = monthsShort;
    this.monthsShortStandalone = monthsShortStandalone;
    this.quartersFull = quartersFull;
    this.quartersShort = quartersShort;
    this.timeFormat = timeFormat;
    this.timeFormatFull = timeFormatFull;
    this.timeFormatLong = timeFormatLong;
    this.timeFormatMedium = timeFormatMedium;
    this.timeFormatShort = timeFormatShort;
    this.weekdaysFull = weekdaysFull;
    this.weekdaysFullStandalone = weekdaysFullStandalone;
    this.weekdaysNarrow = weekdaysNarrow;
    this.weekdaysNarrowStandalone = weekdaysNarrowStandalone;
    this.weekdaysShort = weekdaysShort;
    this.weekdaysShortStandalone = weekdaysShortStandalone;
    this.weekendEnd = weekendEnd;
    this.weekendStart = weekendStart;
  }

  public String[] ampms() {
    return ampms;
  }

  public String dateFormat() {
    return dateFormat;
  }

  public String dateFormatFull() {
    return dateFormatFull;
  }

  public String dateFormatLong() {
    return dateFormatLong;
  }

  public String dateFormatMedium() {
    return dateFormatMedium;
  }

  public String dateFormatShort() {
    return dateFormatShort;
  }

  public String dateTime(String s, String s1) {
    return dateTime.onCallback(s, s1);
  }

  public String dateTimeFull(String s, String s1) {
    return dateTimeFull.onCallback(s, s1);
  }

  public String dateTimeLong(String s, String s1) {
    return dateTimeLong.onCallback(s, s1);
  }

  public String dateTimeMedium(String s, String s1) {
    return dateTimeMedium.onCallback(s, s1);
  }

  public String dateTimeShort(String s, String s1) {
    return dateTimeShort.onCallback(s, s1);
  }

  public String[] erasFull() {
    return erasFull;
  }

  public String[] erasShort() {
    return erasShort;
  }

  public int firstDayOfTheWeek() {
    return firstDayOfTheWeek;
  }

  public String formatDay() {
    return formatDay;
  }

  public String formatHour12Minute() {
    return formatHour12Minute;
  }

  public String formatHour12MinuteSecond() {
    return formatHour12MinuteSecond;
  }

  public String formatHour24Minute() {
    return formatHour24Minute;
  }

  public String formatHour24MinuteSecond() {
    return formatHour24MinuteSecond;
  }

  public String formatMinuteSecond() {
    return formatMinuteSecond;
  }

  public String formatMonthAbbrev() {
    return formatMonthAbbrev;
  }

  public String formatMonthAbbrevDay() {
    return formatMonthAbbrevDay;
  }

  public String formatMonthFull() {
    return formatMonthFull;
  }

  public String formatMonthFullDay() {
    return formatMonthFullDay;
  }

  public String formatMonthFullWeekdayDay() {
    return formatMonthFullWeekdayDay;
  }

  public String formatMonthNumDay() {
    return formatMonthNumDay;
  }

  public String formatYear() {
    return formatYear;
  }

  public String formatYearMonthAbbrev() {
    return formatYearMonthAbbrev;
  }

  public String formatYearMonthAbbrevDay() {
    return formatYearMonthAbbrevDay;
  }

  public String formatYearMonthFull() {
    return formatYearMonthFull;
  }

  public String formatYearMonthFullDay() {
    return formatYearMonthFullDay;
  }

  public String formatYearMonthNum() {
    return formatYearMonthNum;
  }

  public String formatYearMonthNumDay() {
    return formatYearMonthNumDay;
  }

  public String formatYearMonthWeekdayDay() {
    return formatYearMonthWeekdayDay;
  }

  public String formatYearQuarterFull() {
    return formatYearQuarterFull;
  }

  public String formatYearQuarterShort() {
    return formatYearQuarterShort;
  }

  public String[] monthsFull() {
    return monthsFull;
  }

  public String[] monthsFullStandalone() {
    return monthsFullStandalone;
  }

  public String[] monthsNarrow() {
    return monthsNarrow;
  }

  public String[] monthsNarrowStandalone() {
    return monthsNarrowStandalone;
  }

  public String[] monthsShort() {
    return monthsShort;
  }

  public String[] monthsShortStandalone() {
    return monthsShortStandalone;
  }

  public String[] quartersFull() {
    return quartersFull;
  }

  public String[] quartersShort() {
    return quartersShort;
  }

  public String timeFormat() {
    return timeFormat;
  }

  public String timeFormatFull() {
    return timeFormatFull;
  }

  public String timeFormatLong() {
    return timeFormatLong;
  }

  public String timeFormatMedium() {
    return timeFormatMedium;
  }

  public String timeFormatShort() {
    return timeFormatShort;
  }

  public String[] weekdaysFull() {
    return weekdaysFull;
  }

  public String[] weekdaysFullStandalone() {
    return weekdaysFullStandalone;
  }

  public String[] weekdaysNarrow() {
    return weekdaysNarrow;
  }

  public String[] weekdaysNarrowStandalone() {
    return weekdaysNarrowStandalone;
  }

  public String[] weekdaysShort() {
    return weekdaysShort;
  }

  public String[] weekdaysShortStandalone() {
    return weekdaysShortStandalone;
  }

  public int weekendEnd() {
    return weekendEnd;
  }

  public int weekendStart() {
    return weekendStart;
  }
}

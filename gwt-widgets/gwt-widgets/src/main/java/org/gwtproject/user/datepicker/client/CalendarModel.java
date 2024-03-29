/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwtproject.user.datepicker.client;

import java.util.Date;
import org.gwtproject.i18n.client.DateTimeFormat;
import org.gwtproject.i18n.client.DateTimeFormat.PredefinedFormat;

/** Model used to get calendar information for {@link DatePicker} and its subclasses. */
@SuppressWarnings(/* Required to use Date API in gwt */ {"deprecation"})
public class CalendarModel {

  /** The number of weeks normally displayed in a month. */
  public static final int WEEKS_IN_MONTH = 6;

  /** Number of days normally displayed in a week. */
  public static final int DAYS_IN_WEEK = 7;

  public static final int MONTHS_IN_YEAR = 12;

  private static final int MAX_DAYS_IN_MONTH = 32;

  private static final String[] dayOfWeekNames = new String[7];

  private static String[] dayOfMonthNames = new String[32];

  private final String[] monthOfYearNames = new String[12];

  private final Date currentMonth;

  /** Constructor. */
  public CalendarModel() {
    currentMonth = new Date();

    CalendarUtil.setToFirstDayOfMonth(currentMonth);

    // Finding day of week names
    Date date = new Date();
    for (int i = 1; i <= DAYS_IN_WEEK; i++) {
      date.setDate(i);
      int dayOfWeek = date.getDay();
      dayOfWeekNames[dayOfWeek] = getDayOfWeekFormatter().format(date);
    }

    // Finding day of month names
    date.setMonth(0);

    for (int i = 1; i < MAX_DAYS_IN_MONTH; ++i) {
      date.setDate(i);
      dayOfMonthNames[i] = getDayOfMonthFormatter().format(date);
    }

    // finding month names
    date.setDate(1);

    for (int i = 0; i < MONTHS_IN_YEAR; ++i) {
      date.setMonth(i);
      monthOfYearNames[i] = getMonthFormatter().format(date);
    }
  }

  /**
   * Formats the current specified month. For example "September" in English.
   *
   * @return the formatted month
   */
  public String formatCurrentMonth() {
    return getMonthFormatter().format(getCurrentMonth());
  }

  /**
   * Format the current month and year in the current locale. For example, "Jan 2013" in English.
   *
   * @return the formatted month and year
   */
  public String formatCurrentMonthAndYear() {
    return getMonthAndYearFormatter().format(getCurrentMonth());
  }

  /**
   * Formats the current specified year. For example "2012".
   *
   * @return the formatted year
   */
  public String formatCurrentYear() {
    return getYearFormatter().format(getCurrentMonth());
  }

  /**
   * Formats a date's day of month. For example "1".
   *
   * @param date the date
   * @return the formated day of month
   */
  public String formatDayOfMonth(Date date) {
    return dayOfMonthNames[date.getDate()];
  }

  /**
   * Format a day in the week. So, for example "Monday".
   *
   * @param dayInWeek the day in week to format
   * @return the formatted day in week
   */
  public String formatDayOfWeek(int dayInWeek) {
    return dayOfWeekNames[dayInWeek];
  }

  /**
   * Format a month in the year. So, for example "January".
   *
   * @param month A number from 0 (for January) to 11 (for December) identifying the month wanted.
   * @return the formatted month
   */
  public String formatMonth(int month) {
    return monthOfYearNames[month];
  }

  /**
   * Gets the first day of the first week in the currently specified month.
   *
   * @return the first day
   */
  public Date getCurrentFirstDayOfFirstWeek() {
    int wkDayOfMonth1st = currentMonth.getDay();
    int start = CalendarUtil.getStartingDayOfWeek();
    if (wkDayOfMonth1st == start) {
      // always return a copy to allow SimpleCalendarView to adjust first
      // display date
      return new Date(currentMonth.getTime());
    } else {
      Date d = new Date(currentMonth.getTime());
      int offset =
          wkDayOfMonth1st - start > 0
              ? wkDayOfMonth1st - start
              : DAYS_IN_WEEK - (start - wkDayOfMonth1st);
      CalendarUtil.addDaysToDate(d, -offset);
      return d;
    }
  }

  /**
   * Gets the date representation of the currently specified month. Used to access both the month
   * and year information.
   *
   * @return the month and year
   */
  public Date getCurrentMonth() {
    return currentMonth;
  }

  /**
   * Is a date in the currently specified month?
   *
   * @param date the date
   * @return date
   */
  public boolean isInCurrentMonth(Date date) {
    return currentMonth.getMonth() == date.getMonth();
  }

  /**
   * Sets the currently specified date.
   *
   * @param currentDate the currently specified date
   */
  public void setCurrentMonth(Date currentDate) {
    this.currentMonth.setYear(currentDate.getYear());
    this.currentMonth.setMonth(currentDate.getMonth());
  }

  /**
   * Shifts the currently specified date by the given number of months. The day of the month will be
   * pinned to the original value as far as possible.
   *
   * @param deltaMonths - number of months to be added to the current date
   */
  public void shiftCurrentMonth(int deltaMonths) {
    CalendarUtil.addMonthsToDate(currentMonth, deltaMonths);
    refresh();
  }

  /**
   * Gets the date of month formatter.
   *
   * @return the day of month formatter
   */
  protected DateTimeFormat getDayOfMonthFormatter() {
    return DateTimeFormat.getFormat("d");
  }

  /**
   * Gets the day of week formatter.
   *
   * @return the day of week formatter
   */
  protected DateTimeFormat getDayOfWeekFormatter() {
    return DateTimeFormat.getFormat("ccccc");
  }

  /**
   * Gets the month and year formatter.
   *
   * @return the month and year formatter
   */
  protected DateTimeFormat getMonthAndYearFormatter() {
    return DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_ABBR);
  }

  /**
   * Gets the month formatter.
   *
   * @return the month formatter
   */
  protected DateTimeFormat getMonthFormatter() {
    return DateTimeFormat.getFormat(PredefinedFormat.MONTH_ABBR);
  }

  /**
   * Gets the year formatter.
   *
   * @return the year formatter
   */
  protected DateTimeFormat getYearFormatter() {
    return DateTimeFormat.getFormat(PredefinedFormat.YEAR);
  }

  /** Returns {@code true} if the month is before year in the date formatter in current locale. */
  protected boolean isMonthBeforeYear() {
    String monthAndYearPattern = getMonthAndYearFormatter().getPattern();

    for (int i = 0; i < monthAndYearPattern.length(); ++i) {
      switch (monthAndYearPattern.charAt(i)) {
        case 'y':
          return false;
        case 'M':
        case 'L':
          return true;
      }
    }

    return true;
  }

  /** Refresh the current model as needed. */
  protected void refresh() {}
}

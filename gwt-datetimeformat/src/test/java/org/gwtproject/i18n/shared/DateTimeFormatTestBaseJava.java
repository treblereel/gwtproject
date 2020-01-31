/*
 * Copyright 2010 Google Inc.
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
package org.gwtproject.i18n.shared;

import junit.framework.TestCase;
import org.gwtproject.i18n.shared.DateTimeFormat.PredefinedFormat;

import java.util.Date;

// import org.gwtproject.i18n.client.Messages;

/** Base class for date/time format tests. */
public abstract class DateTimeFormatTestBaseJava extends TestCase {

  /** The timezone used by any tests which use a fixed timezone. */
  protected static final TimeZone TEST_TIMEZONE =
      org.gwtproject.i18n.client.TimeZone.createTimeZone(300);

  //  /**
  //   * Test date/time formats in messages.
  //   */
  //  public interface MyMessages extends Messages {
  //    @DefaultMessage("It is {0,localdatetime,dMMMy}")
  //    String getCustomizedDate(Date date);
  //  }

  protected void setLocale(String locale) {
    System.setProperty("locale", locale);
  }

  @SuppressWarnings("deprecation")
  public void testIso8601() {
    DateTimeFormat dtf = DateTimeFormat.getFormat(PredefinedFormat.ISO_8601);
    Date date = new Date(Date.UTC(2006 - 1900, 6, 27, 13, 10, 10));
    String str = dtf.format(date, TEST_TIMEZONE);
    assertEquals("2006-07-27T08:10:10.000-05:00", str);

    date = dtf.parse("2006-07-27T13:10:10.000Z");
    str = dtf.format(date, TEST_TIMEZONE);
    assertEquals("2006-07-27T08:10:10.000-05:00", str);
  }

  @SuppressWarnings("deprecation")
  public void testRfc2822() {
    DateTimeFormat dtf = DateTimeFormat.getFormat(PredefinedFormat.RFC_2822);
    Date date = new Date(Date.UTC(2006 - 1900, 6, 27, 13, 10, 10));
    String str = dtf.format(date, TEST_TIMEZONE);
    assertEquals("Thu, 27 Jul 2006 08:10:10 -0500", str);
  }
}

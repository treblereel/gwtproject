/*
 * Copyright 2009 Google Inc.
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
package org.gwtproject.i18n.client;

import java.util.Date;
import org.gwtproject.i18n.shared.DateTimeFormatTestBase;

/** Tests unique functionality in {@link DateTimeFormat} for the Polish language. */
@SuppressWarnings("deprecation")
public class DateTimeFormat_pl_Test extends DateTimeFormatTestBase {

  @Override
  public void setUp() throws Exception {
    setLocale("pl");
  }

  public void test_LL() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("07", DateTimeFormat.getFormat("LL").format(date));
  }

  public void test_LLL() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("lip", DateTimeFormat.getFormat("LLL").format(date));
  }

  public void test_LLLL() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("lipiec", DateTimeFormat.getFormat("LLLL").format(date));
  }

  public void test_LLLLL() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("L", DateTimeFormat.getFormat("LLLLL").format(date));
  }

  public void test_MM() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("07", DateTimeFormat.getFormat("MM").format(date));
  }

  public void test_MMM() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("lip", DateTimeFormat.getFormat("MMM").format(date));
  }

  public void test_MMMM() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("lipca", DateTimeFormat.getFormat("MMMM").format(date));
  }

  public void test_MMMMM() {
    Date date = new Date(2006 - 1900, 6, 27, 13, 10, 10);
    assertEquals("l", DateTimeFormat.getFormat("MMMMM").format(date));
  }
}

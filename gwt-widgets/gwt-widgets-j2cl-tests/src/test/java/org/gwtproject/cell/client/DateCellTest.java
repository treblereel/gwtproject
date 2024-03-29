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
package org.gwtproject.cell.client;

import com.google.j2cl.junit.apt.J2clTestInput;
import java.util.Date;
import org.junit.Ignore;

/** Tests for {@link org.gwtproject.cell.client.DateCell}. */
@Ignore
@J2clTestInput(DateCellTest.class)
public class DateCellTest extends CellTestBase<Date> {

  @Override
  protected Cell<Date> createCell() {

    throw new NullPointerException();
    // Set format that shows all fields and timezone of GMT-7
    /*    return new DateCell(DateTimeFormat.getFormat("dd-MM-yyyy HH:mm:ss"),
    TimeZone.createTimeZoneInMinutes(7 * 60));*/
  }

  @Override
  @SuppressWarnings("deprecation")
  protected Date createCellValue() {
    Date date = new Date();
    date.setTime(3600 * 1000 * 10); // In GMT, Jan 1, 1970, 10:00:00
    return date;
  }

  @Override
  protected boolean dependsOnSelection() {
    return false;
  }

  @Override
  protected String[] getConsumedEvents() {
    return null;
  }

  @Override
  protected String getExpectedInnerHtml() {
    return "01-01-1970 03:00:00"; // 10 - 7 == 3
  }

  @Override
  protected String getExpectedInnerHtmlNull() {
    return "";
  }

  @Override
  public String getModuleName() {
    return "";
  }
}

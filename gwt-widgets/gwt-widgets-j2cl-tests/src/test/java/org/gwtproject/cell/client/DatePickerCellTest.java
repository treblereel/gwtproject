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
import org.gwtproject.i18n.client.DateTimeFormat;
import org.gwtproject.i18n.client.DateTimeFormat.PredefinedFormat;

/** Tests for {@link org.gwtproject.cell.client.DatePickerCell}. */
@J2clTestInput(DatePickerCellTest.class)
public class DatePickerCellTest extends EditableCellTestBase<Date, Date> {

  @Override
  protected org.gwtproject.cell.client.DatePickerCell createCell() {
    return new DatePickerCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM));
  }

  @Override
  protected Date createCellValue() {
    return new Date(2010 - 1900, 0, 1);
  }

  @Override
  protected Date createCellViewData() {
    return new Date(2010 - 1900, 0, 3);
  }

  @Override
  protected boolean dependsOnSelection() {
    return false;
  }

  @Override
  protected String[] getConsumedEvents() {
    return new String[] {"click", "keydown"};
  }

  // TODO for en_US Jan 1, 2010, must redone when i18n ll be finished
  @Override
  protected String getExpectedInnerHtml() {
    return "2010 Jan 1"; // for default locale
    // return "Jan 1, 2010";
  }

  @Override
  protected String getExpectedInnerHtmlNull() {
    return "";
  }

  // TODO for en_US Jan 3, 2010, must redone when i18n ll be finished
  @Override
  protected String getExpectedInnerHtmlViewData() {
    return "2010 Jan 3"; // for default locale
    // return "Jan 3, 2010";
  }

  @Override
  public String getModuleName() {
    return "";
  }
}

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
import java.util.ArrayList;
import java.util.List;
import org.gwtproject.dom.client.Document;
import org.gwtproject.dom.client.NativeEvent;

/** Tests for {@link org.gwtproject.cell.client.SelectionCell}. */
@J2clTestInput(SelectionCellTest.class)
public class SelectionCellTest extends EditableCellTestBase<String, String> {

  public void testOnBrowser() {
    NativeEvent event = Document.get().createChangeEvent();
    testOnBrowserEvent(getExpectedInnerHtml(), event, "option 0", null, "option 1", "option 1");
  }

  @Override
  protected org.gwtproject.cell.client.SelectionCell createCell() {
    List<String> options = new ArrayList<String>();
    for (int i = 0; i < 3; i++) {
      options.add("option " + i);
    }
    return new SelectionCell(options);
  }

  @Override
  protected String createCellValue() {
    return "option 1";
  }

  @Override
  protected String createCellViewData() {
    return "option 2";
  }

  @Override
  protected boolean dependsOnSelection() {
    return false;
  }

  @Override
  protected String[] getConsumedEvents() {
    return new String[] {"change", "keydown", "focus", "blur"};
  }

  @Override
  protected String getExpectedInnerHtml() {
    return "<select tabindex=\"-1\"><option value=\"option 0\">option 0</option>"
        + "<option value=\"option 1\" selected=\"selected\">option 1</option>"
        + "<option value=\"option 2\">option 2</option></select>";
  }

  @Override
  protected String getExpectedInnerHtmlNull() {
    return "<select tabindex=\"-1\"><option value=\"option 0\">option 0</option>"
        + "<option value=\"option 1\">option 1</option>"
        + "<option value=\"option 2\">option 2</option></select>";
  }

  @Override
  protected String getExpectedInnerHtmlViewData() {
    return "<select tabindex=\"-1\"><option value=\"option 0\">option 0</option>"
        + "<option value=\"option 1\">option 1</option>"
        + "<option value=\"option 2\" selected=\"selected\">option 2</option></select>";
  }

  @Override
  public String getModuleName() {
    return "";
  }
}

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
package org.gwtproject.i18n.client;

import static org.gwtproject.i18n.client.Messages.*;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests custom plural rules, including as an inner class.
 *
 * <p>Captures issue 4175.
 */
public class CustomPluralsTest extends GWTTestCase {

  /** Messages interface with a custom plural rule. */
  @Message
  public interface MyMessages extends Messages {

    @DefaultMessage("other: {0}")
    @AlternateMessage({"0", "zero", "1", "one"})
    String customPlural(@PluralCount(CustomPluralRule.class) int cnt);
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.i18n.I18NTest";
  }

  public void testCustomPluralRule() {
    MyMessages m = CustomPluralsTestMyMessagesFactory.get();
    assertEquals("zero", m.customPlural(0));
    assertEquals("one", m.customPlural(1));
    assertEquals("other: 2", m.customPlural(2));
  }
}

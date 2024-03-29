/*
 * Copyright 2007 Google Inc.
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

import com.google.gwt.junit.client.GWTTestCase;

/** Tests regional inheritance for es_MX. */
public class I18N_es_MX_Test extends GWTTestCase {

  /** Test locale region inheritance with Messages. */
  @Message
  public interface MyMessages extends Messages {
    @DefaultMessage("default")
    String getSourceLocale();
  }

  /** Test locale region inheritance with Constants. */
  @Constant
  public interface MyConstants extends Constants {
    @DefaultStringValue("default")
    String getSourceLocale();
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.i18n.I18NTest_es_MX";
  }

  public void testRegionalInheritance() {
    MyMessages msg = I18N_es_MX_TestMyMessagesFactory.get();
    assertEquals("es_419", msg.getSourceLocale());
    MyConstants cst = I18N_es_MX_TestMyConstantsFactory.get();
    assertEquals("es_013", cst.getSourceLocale());
  }
}

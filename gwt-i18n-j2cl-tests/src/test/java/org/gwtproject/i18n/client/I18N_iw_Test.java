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

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.core.client.GWT;
import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests Hebrew deprecated alias.
 */
//@J2clTestInput(I18N_iw_Test.class)
public class I18N_iw_Test {

  /**
   * Test deprecated locale aliases with Constants.
   */
  public interface MyConstants extends Constants {
    @DefaultStringValue("default")
    String getSourceLocale();
  }
  
  /**
   * Test deprecated locale aliases with Messages.
   */
  public interface MyMessages extends Messages {
    @DefaultMessage("default")
    String getSourceLocale();
  }

  @Before
  public void setUp() {
    LocaleInfoFactory.locale = "iw";
  }

  @Test
  public void testCldrImpl() {
    assertTrue(LocaleInfo.getCurrentLocale().isRTL());
  }

  public void testIwAlias() {
    MyMessages msg = GWT.create(MyMessages.class);
    assertEquals("he", msg.getSourceLocale());
    MyConstants cst = GWT.create(MyConstants.class);
    assertEquals("he", cst.getSourceLocale());
  }
}

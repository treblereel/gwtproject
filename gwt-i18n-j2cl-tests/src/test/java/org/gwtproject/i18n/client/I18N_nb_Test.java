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
import org.gwtproject.i18n.shared.cldr.CurrencyData;
import org.gwtproject.i18n.shared.cldr.CurrencyList;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Norwegian Bokmal locale.
 */
@J2clTestInput(I18N_nb_Test.class)
public class I18N_nb_Test {

  @Before
  public void setUp() {
    LocaleInfoFactory.locale = "nb";
  }

  /**
   * Test deprecated locale aliases with Messages.
   */
  public interface MyMessages extends Messages {
    @DefaultMessage("default")
    String nbLocale();

    @DefaultMessage("default")
    String noBokmalLocale();

    @DefaultMessage("default")
    String noLocale();
  }
  
  /**
   * Test alias resolution.
   */
  public void testAliases() {
    MyMessages msg = GWT.create(MyMessages.class);
    assertEquals("nb", msg.nbLocale());
    assertEquals("no_BOKMAL", msg.noBokmalLocale());
    assertEquals("no", msg.noLocale());
  }
  
  /**
   * Test currency codes.
   */
  @Test
  public void testCurrency() {
    CurrencyList currencyList = CurrencyList.get();
    CurrencyData currencyData = currencyList.getDefault();
    assertNotNull(currencyData);
    assertEquals("NOK", currencyData.getCurrencyCode());
    assertEquals("kr", currencyData.getCurrencySymbol());
    currencyData = currencyList.lookup("RUB");
    assertNotNull(currencyData);
    assertEquals("RUB", currencyData.getCurrencyCode());
    assertEquals("руб.", currencyData.getCurrencySymbol());
  }
}

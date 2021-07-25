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
package org.gwtproject.i18n.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.i18n.shared.cldr.LocaleInfo;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the LocaleInfo class and the associated generator.
 */
@J2clTestInput(LocaleInfo_en_Test.class)
public class LocaleInfo_en_Test {

  @Before
  public void setUp() {
    //LocaleInfoFactory.locale = "en";  }

/*  public void testAvailableLocales() {
    String[] locales = LocaleInfo.getAvailableLocaleNames();
    ArrayList<String> localeList = new ArrayList<String>();
    Collections.addAll(localeList, locales);
    assertTrue(localeList.contains("ar"));
    assertTrue(localeList.contains("default"));
    assertTrue(localeList.contains("en_US"));
  }*/

/*  public void testCookieName() {
    String cookieName = LocaleInfo.getCurrentLocale().getLocaleCookieName();
    assertEquals("LOCALE", cookieName);
  }

  public void testQueryParam() {
    String queryParam = LocaleInfo.getCurrentLocale().getLocaleQueryParam();
    assertNull(queryParam); */
  }

  @Test
  public void testRTL() {
    boolean isRTL = LocaleInfo.getCurrentLocale().isRTL();
    assertFalse(isRTL);

    //TODO
    //boolean hasRTL = LocaleInfo.hasAnyRTL();
    //assertTrue(hasRTL);
  }
}

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

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Validate NumberFormat handles Arabic numbers properly.
 */
@J2clTestInput(NumberFormat_ar_Test.class)
public class NumberFormat_ar_Test {

  @Before
  public void setUp() {
    LocaleInfoFactory.locale = "ar";
  }

  @Test
  public void testDefault() {
    NumberFormat fmt = NumberFormat.getDecimalFormat();
    assertEquals("٣٫١٤", fmt.format(3.14));
    assertEquals("\u061C-٣٫١٤", fmt.format(-3.14));
  }

  @Test
  public void testExponent() {
    NumberFormat fmt = NumberFormat.getScientificFormat();
    assertEquals("٣اس٠",
        fmt.format(3.14));
    assertEquals("\u0663\u0627\u0633\u0662",
        fmt.format(314.0));
    assertEquals("\u061C-٣اس٢", fmt.format(-314.0));
  }

  //@Test
  public void testForceLatin() {
    assertFalse(NumberFormat.forcedLatinDigits());
    NumberFormat.setForcedLatinDigits(true);
    assertTrue(NumberFormat.forcedLatinDigits());
    NumberFormat decLatin = NumberFormat.getDecimalFormat();
    assertEquals("1\u00A0003,14", decLatin.format(1003.14));
    assertEquals("\u200F-1\u00A0003,14", decLatin.format(-1003.14));
    NumberFormat.setForcedLatinDigits(false);
    assertFalse(NumberFormat.forcedLatinDigits());
    assertEquals("3,14", decLatin.format(3.14));
    NumberFormat decArabic = NumberFormat.getDecimalFormat();
    assertEquals("\u0663\u066B\u0661\u0664", decArabic.format(3.14));
  }

  @Test
  public void testParse() {
    NumberFormat fmt = NumberFormat.getDecimalFormat();

    assertEquals(3.14, fmt.parse("\u0663\u066B\u0661\u0664"), 0.1);
    //assertEquals(-3.14, fmt.parse("\u200F-\u0663\u066B\u0661\u0664"), 0.1);
    assertEquals(314.0, fmt.parse("\u0663\u0661\u0664"), 0.1);
  }
}

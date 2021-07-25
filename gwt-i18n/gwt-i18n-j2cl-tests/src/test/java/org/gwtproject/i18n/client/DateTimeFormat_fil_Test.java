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

import static org.junit.Assert.*;

import java.util.Date;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.i18n.shared.cldr.impl.LocaleInfoFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests unique functionality in {@link DateTimeFormat} for the Filipino
 * language.
 */
@SuppressWarnings("deprecation")
@J2clTestInput(DateTimeFormat_fil_Test.class)
public class DateTimeFormat_fil_Test {

  @Before
  public void setUp() {
    //LocaleInfoFactory.locale = "fil";
  }

  @Test
  public void test_ccc() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miy", DateTimeFormat.getFormat("ccc").format(date));
  }

  @Test
  public void test_cccc() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miyerkules", DateTimeFormat.getFormat("cccc").format(date));
  }

  @Test
  public void test_ccccc() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miy", DateTimeFormat.getFormat("ccccc").format(date));
  }

  @Test
  public void test_EEE() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miy", DateTimeFormat.getFormat("EEE").format(date));
  }

  @Test
  public void test_EEEE() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miyerkules", DateTimeFormat.getFormat("EEEE").format(date));
  }

  @Test
  public void test_EEEEE() {
    Date date = new Date(2006 - 1900, 6, 26, 13, 10, 10);
    assertEquals("Miy", DateTimeFormat.getFormat("EEEEE").format(date));
  }
}

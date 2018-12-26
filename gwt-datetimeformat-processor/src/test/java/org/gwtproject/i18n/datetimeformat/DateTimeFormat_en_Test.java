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
package org.gwtproject.i18n.datetimeformat;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Tests formatting functionality in {@link org.gwtproject.i18n.shared.DateTimeFormat} for the
 * English language.
 */
public class DateTimeFormat_en_Test {

    @Before
    public void setUp() throws Exception {
        System.setProperty("locale", "en");
    }

    @Test
    public void testCustomFormats() {
        MyFormats m = new MyFormats_en();
        Date d = new Date(2010 - 1900, 1, 15, 12, 0, 0);
        assertThat("Feb 15, 2010").isEqualTo(m.yearMonthDayAbbrev().format(d));
        assertThat("February 15, 2010").isEqualTo(m.yearMonthDayFull().format(d));
        assertThat("February 15, 2010").isEqualTo(m.yearMonthDayFull2().format(d));
    }
}

/*
 * Copyright 2013 Google Inc.
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
package org.gwtproject.i18n;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import org.gwtproject.i18n.client.DateTimeFormat_de_Test;
import org.gwtproject.i18n.client.DateTimeFormat_en_Test;
import org.gwtproject.i18n.client.DateTimeFormat_fil_Test;
import org.gwtproject.i18n.client.DateTimeFormat_pl_Test;
import org.gwtproject.i18n.client.DateTimeParse_en_Test;
import org.gwtproject.i18n.client.DateTimeParse_zh_CN_Test;
import org.gwtproject.i18n.client.LocaleInfoTest;
import org.gwtproject.i18n.client.LocaleInfo_ar_Test;
import org.gwtproject.i18n.client.NumberFormat_ar_Test;
import org.gwtproject.i18n.client.NumberFormat_en_Test;
import org.gwtproject.i18n.client.NumberParse_en_Test;
import org.gwtproject.i18n.client.NumberParse_fr_Test;

/** I18N tests for client code running as a GWT test. */
public class I18NSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("I18N client tests");

    suite.addTestSuite(DateTimeFormat_de_Test.class);
    suite.addTestSuite(DateTimeFormat_en_Test.class);
    suite.addTestSuite(DateTimeFormat_fil_Test.class);
    suite.addTestSuite(DateTimeFormat_pl_Test.class);
    suite.addTestSuite(DateTimeParse_en_Test.class);
    suite.addTestSuite(DateTimeParse_zh_CN_Test.class);

    suite.addTestSuite(LocaleInfo_ar_Test.class);
    suite.addTestSuite(LocaleInfoTest.class);

    // suite.addTestSuite(LocalizedNames_default_Test.class);
    // suite.addTestSuite(LocalizedNames_en_Test.class);

    suite.addTestSuite(NumberFormat_ar_Test.class);
    suite.addTestSuite(NumberFormat_en_Test.class);
    // suite.addTestSuite(NumberFormat_fr_Test.class); TODO
    suite.addTestSuite(NumberParse_en_Test.class);
    suite.addTestSuite(NumberParse_fr_Test.class);

    return suite;
  }
}

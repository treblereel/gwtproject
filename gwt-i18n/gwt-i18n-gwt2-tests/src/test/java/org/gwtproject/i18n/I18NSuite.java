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
import org.gwtproject.i18n.client.AnnotationsTest;
import org.gwtproject.i18n.client.ArabicPluralsTest;
import org.gwtproject.i18n.client.CurrencyTest;
import org.gwtproject.i18n.client.CustomPluralsTest;
import org.gwtproject.i18n.client.DateTimeFormat_de_Test;
import org.gwtproject.i18n.client.DateTimeFormat_en_Test;
import org.gwtproject.i18n.client.DateTimeFormat_fil_Test;
import org.gwtproject.i18n.client.DateTimeFormat_pl_Test;
import org.gwtproject.i18n.client.DateTimeParse_en_Test;
import org.gwtproject.i18n.client.DateTimeParse_zh_CN_Test;
import org.gwtproject.i18n.client.I18N2Test;
import org.gwtproject.i18n.client.I18NTest;
import org.gwtproject.i18n.client.I18N_en_US_Test;
import org.gwtproject.i18n.client.I18N_es_MX_RuntimeTest;
import org.gwtproject.i18n.client.I18N_es_MX_Test;
import org.gwtproject.i18n.client.I18N_he_Test;
import org.gwtproject.i18n.client.I18N_nb_Test;
import org.gwtproject.i18n.client.I18N_pa_Arab_Test;
import org.gwtproject.i18n.client.I18N_pa_PK_Test;
import org.gwtproject.i18n.client.LocaleInfoTest;
import org.gwtproject.i18n.client.LocaleInfo_ar_Test;
import org.gwtproject.i18n.client.NumberFormat_ar_Test;
import org.gwtproject.i18n.client.NumberFormat_en_Test;
import org.gwtproject.i18n.client.NumberFormat_fr_Test;
import org.gwtproject.i18n.client.NumberParse_en_Test;
import org.gwtproject.i18n.client.NumberParse_fr_Test;
import org.gwtproject.i18n.client.PolishPluralsTest;
import org.gwtproject.i18n.client.RuntimeLocalesTest;
import org.gwtproject.i18n.client.RussianPluralsTest;
import org.gwtproject.i18n.client.TimeZoneInfoTest;
import org.gwtproject.i18n.client.TimeZoneTest;
import org.gwtproject.i18n.shared.GwtBidiUtilsTest;
import org.gwtproject.i18n.shared.cldr.I18N;

/** I18N tests for client code running as a GWT test. */
@I18N({"fil", "pl", "en", "zh_CN", "pa_Arab", "pa_PK", "he", "es_MX", "en_US", "nb", "ar", "fr"})
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

    suite.addTestSuite(ArabicPluralsTest.class);
    suite.addTestSuite(AnnotationsTest.class);
    suite.addTestSuite(CurrencyTest.class);
    suite.addTestSuite(CustomPluralsTest.class);
    suite.addTestSuite(DateTimeFormat_fil_Test.class);
    suite.addTestSuite(DateTimeFormat_pl_Test.class);
    suite.addTestSuite(DateTimeParse_en_Test.class);
    suite.addTestSuite(DateTimeParse_zh_CN_Test.class);
    suite.addTestSuite(GwtBidiUtilsTest.class);
    suite.addTestSuite(I18NTest.class);
    suite.addTestSuite(I18N2Test.class);
    suite.addTestSuite(I18N_pa_Arab_Test.class);
    suite.addTestSuite(I18N_pa_PK_Test.class);
    suite.addTestSuite(I18N_he_Test.class);
    suite.addTestSuite(I18N_es_MX_Test.class);
    suite.addTestSuite(I18N_es_MX_RuntimeTest.class);
    suite.addTestSuite(I18N_en_US_Test.class);
    suite.addTestSuite(I18N_nb_Test.class);
    suite.addTestSuite(LocaleInfo_ar_Test.class);
    suite.addTestSuite(LocaleInfoTest.class);
    suite.addTestSuite(NumberFormat_ar_Test.class);
    suite.addTestSuite(NumberFormat_en_Test.class);
    suite.addTestSuite(NumberFormat_fr_Test.class);
    suite.addTestSuite(NumberParse_en_Test.class);
    suite.addTestSuite(NumberParse_fr_Test.class);
    suite.addTestSuite(PolishPluralsTest.class);
    suite.addTestSuite(RussianPluralsTest.class);
    suite.addTestSuite(RuntimeLocalesTest.class);
    suite.addTestSuite(TimeZoneInfoTest.class);
    suite.addTestSuite(TimeZoneTest.class);

    return suite;
  }
}

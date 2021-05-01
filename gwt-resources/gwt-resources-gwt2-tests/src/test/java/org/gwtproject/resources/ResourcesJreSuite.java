/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.resources;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.gwtproject.resources.rg.CssClassNamesTestCase;
import org.gwtproject.resources.rg.css.*;

/** @author Dmitrii Tikhomirov Created by treblereel 11/30/18 */
public class ResourcesJreSuite {
  public static Test suite() {

    TestSuite suite = new TestSuite("JRE test for com.google.gwt.resources");
    suite.addTestSuite(CssClassNamesTestCase.class);
    suite.addTestSuite(CssExternalTest.class);
    suite.addTestSuite(CssNodeClonerTest.class);
    // suite.addTestSuite(CssReorderTest.class);
    // suite.addTestSuite(CssRtlTest.class);
    // suite.addTestSuite(ExtractClassNamesVisitorTest.class);
    // suite.addTestSuite(ResourceGeneratorUtilTest.class);
    suite.addTestSuite(UnknownAtRuleTest.class);

    // GSS tests
    /*        suite.addTestSuite(ExternalClassesCollectorTest.class);
    suite.addTestSuite(RenamingSubstitutionMapTest.class);
    suite.addTestSuite(ImageSpriteCreatorTest.class);
    suite.addTestSuite(ClassNamesCollectorTest.class);
    suite.addTestSuite(CssPrinterTest.class);
    suite.addTestSuite(PermutationsCollectorTest.class);
    suite.addTestSuite(RecordingBidiFlipperTest.class);
    suite.addTestSuite(ResourceUrlFunctionTest.class);
    suite.addTestSuite(ExtendedEliminateConditionalNodesTest.class);
    suite.addTestSuite(PermutationsCollectorTest.class);
    suite.addTestSuite(ResourceUrlFunctionTest.class);
    suite.addTestSuite(RuntimeConditionalBlockCollectorTest.class);
    suite.addTestSuite(ValidateRuntimeConditionalNodeTest.class);
    suite.addTestSuite(ValueFunctionTest.class);
    suite.addTestSuite(BooleanConditionCollectorTest.class);*/

    // CSS to GSS converter tests
    /*        suite.addTestSuite(Css2GssTest.class);
    suite.addTestSuite(CssOutputTestCase.class);
    suite.addTestSuite(DefCollectorVisitorTest.class);
    suite.addTestSuite(ElseNodeCreatorTest.class);
    suite.addTestSuite(AlternateAnnotationCreatorVisitorTest.class);
    suite.addTestSuite(UndefinedConstantVisitorTest.class);*/

    return suite;
  }
}

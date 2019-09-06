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
package org.gwtproject.validation;

import org.gwtproject.validation.testing.constraints.AssertFalseValidatorTest;
import org.gwtproject.validation.testing.constraints.AssertTrueValidatorTest;
import org.gwtproject.validation.testing.constraints.DecimalMaxValidatorForNumberTest;
import org.gwtproject.validation.testing.constraints.DecimalMaxValidatorForStringTest;
import org.gwtproject.validation.testing.constraints.DecimalMinValidatorForNumberTest;
import org.gwtproject.validation.testing.constraints.DecimalMinValidatorForStringTest;
import org.gwtproject.validation.testing.constraints.DigitsValidatorForNumberTest;
import org.gwtproject.validation.testing.constraints.DigitsValidatorForStringTest;
import org.gwtproject.validation.testing.constraints.FutureValidatorForDateTest;
import org.gwtproject.validation.testing.constraints.MaxValidatorForNumberTest;
import org.gwtproject.validation.testing.constraints.MaxValidatorForStringTest;
import org.gwtproject.validation.testing.constraints.MinValidatorForNumberTest;
import org.gwtproject.validation.testing.constraints.MinValidatorForStringTest;
import org.gwtproject.validation.testing.constraints.NotNullValidatorTest;
import org.gwtproject.validation.testing.constraints.NullValidatorTest;
import org.gwtproject.validation.testing.constraints.PastValidatorForDateTest;
import org.gwtproject.validation.testing.constraints.PatternValidatorTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfBooleanTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfByteTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfCharTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfDoubleTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfFloatTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfIntTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfLongTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfObjectTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForArrayOfShortTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForCollectionTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForMapTest;
import org.gwtproject.validation.testing.constraints.SizeValidatorForStringTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * All Constraints tests that don't need GWTTestCase.
 */
public class ConstraintsJreSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Validation Constraint tests that require the JRE");
    suite.addTestSuite(AssertFalseValidatorTest.class);
    suite.addTestSuite(AssertTrueValidatorTest.class);
    suite.addTestSuite(DecimalMaxValidatorForNumberTest.class);
    suite.addTestSuite(DecimalMaxValidatorForStringTest.class);
    suite.addTestSuite(DecimalMinValidatorForNumberTest.class);
    suite.addTestSuite(DecimalMinValidatorForStringTest.class);
    suite.addTestSuite(DigitsValidatorForNumberTest.class);
    suite.addTestSuite(DigitsValidatorForStringTest.class);
    suite.addTestSuite(FutureValidatorForDateTest.class);
    suite.addTestSuite(MaxValidatorForNumberTest.class);
    suite.addTestSuite(MaxValidatorForStringTest.class);
    suite.addTestSuite(MinValidatorForNumberTest.class);
    suite.addTestSuite(MinValidatorForStringTest.class);
    suite.addTestSuite(NotNullValidatorTest.class);
    suite.addTestSuite(NullValidatorTest.class);
    suite.addTestSuite(PastValidatorForDateTest.class);
    suite.addTestSuite(PatternValidatorTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfBooleanTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfByteTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfCharTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfDoubleTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfFloatTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfIntTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfLongTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfObjectTest.class);
    suite.addTestSuite(SizeValidatorForArrayOfShortTest.class);
    suite.addTestSuite(SizeValidatorForCollectionTest.class);
    suite.addTestSuite(SizeValidatorForMapTest.class);
    suite.addTestSuite(SizeValidatorForCollectionTest.class);
    suite.addTestSuite(SizeValidatorForMapTest.class);
    suite.addTestSuite(SizeValidatorForStringTest.class);
    return suite;
  }
}

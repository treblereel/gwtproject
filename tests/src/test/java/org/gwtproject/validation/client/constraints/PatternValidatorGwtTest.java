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
package org.gwtproject.validation.client.constraints;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.validation.client.TestValidatorFactory_GwtValidatorImpl;
import org.gwtproject.validation.client.ValidationClientGwtTestCase;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

/**
 * Tests for {@link PatternValidator}.
 */
@J2clTestInput(value = PatternValidatorGwtTest.class)
public class PatternValidatorGwtTest extends ValidationClientGwtTestCase {

  /**
   * Test object that uses {@link Pattern.Flag}.
   */
  public static class Flagged {

    public String getName() {
      return name;
    }

    @Pattern(regexp = "g..d", flags = Flag.CASE_INSENSITIVE)
    public String name;
  }

  private Flagged flagged;

  public void testAssertIsValid_goad() {
    assertValueValid("goad", true);
  }

  public void testAssertIsValid_good() {
    assertValueValid("good", true);
  }

  public void testAssertIsValid_GOOD() {
    assertValueValid("GOOD", true);
  }

  public void testAssertIsValid_goood() {
    assertValueValid("goood", false);
  }

  public void testAssertIsValid_not_good() {
    assertValueValid("this is not good", false);
  }

  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    flagged = new Flagged();
  }

  Validator validator = new TestValidatorFactory_GwtValidatorImpl();

  private void assertValueValid(String value, boolean expected) {
    flagged.name = value;
    Set<ConstraintViolation<Flagged>> violations = validator.validate(flagged);
    assertEquals(value + " is valid", expected, violations.isEmpty());
  }
}

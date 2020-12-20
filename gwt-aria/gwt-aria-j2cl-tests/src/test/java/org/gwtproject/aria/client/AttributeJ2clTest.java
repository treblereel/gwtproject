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
package org.gwtproject.aria.client;

import static junit.framework.TestCase.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.dom.client.DivElement;
import org.gwtproject.dom.client.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@J2clTestInput(AttributeJ2clTest.class)
public class AttributeJ2clTest {

  private DivElement div;
  private Attribute<OrientationValue> attribute1;
  private Attribute<Boolean> attribute2;
  private Attribute<String> attribute3;
  private Attribute<RelevantValue> attribute4;
  private Attribute<Id> attribute5;

  @Test
  public void testSetGetRemove_booleanValue() {
    System.out.println("testSetGetRemove_booleanValue()");
    attribute2.setDefault(div);
    attribute2.set(div, false);
    attribute2.set(div, true);
    assertEquals("true", attribute2.get(div));
    attribute2.set(div, false);
    assertEquals("false", attribute2.get(div));
    attribute2.remove(div);
    assertEquals("", attribute2.get(div));
  }

  @Test
  public void testSetGetRemove_tokenValue() {
    System.out.println("testSetGetRemove_tokenValue()");
    attribute1.setDefault(div);
    assertEquals(OrientationValue.VERTICAL.getAriaValue(), attribute1.get(div));
    attribute1.remove(div);
    assertEquals("", attribute1.get(div));
    attribute1.set(div, OrientationValue.HORIZONTAL);
    assertEquals(OrientationValue.HORIZONTAL.getAriaValue(), attribute1.get(div));
  }

  @Test
  public void testSetGetRemove_tokenListValue() {
    System.out.println("testSetGetRemove_tokenListValue()");
    attribute4.setDefault(div);
    assertEquals(
        RelevantValue.ADDITIONS.getAriaValue() + " " + RelevantValue.TEXT.getAriaValue(),
        attribute4.get(div));
    attribute4.remove(div);
    assertEquals("", attribute1.get(div));
    attribute4.set(div, RelevantValue.REMOVALS);
    assertEquals(RelevantValue.REMOVALS.getAriaValue(), attribute4.get(div));
  }

  @Test
  public void testSetGetRemove_idrefValue() {
    System.out.println("testSetGetRemove_idrefValue()");
    attribute5.set(div, Id.of("1"), Id.of("2"));
    assertEquals("1 2", attribute5.get(div));
    attribute5.remove(div);
    assertEquals("", attribute5.get(div));

    DivElement ref1 = createDiv();
    ref1.setId("ref1");
    DivElement ref2 = createDiv();
    ref2.setId("ref2");
    attribute5.set(div, Id.of(ref1), Id.of(ref2));
    assertEquals("ref1 ref2", attribute5.get(div));
  }

  private DivElement createDiv() {
    return Document.get().createDivElement();
  }

  @Test
  public void testSetDefaultValue_noSet() {
    try {
      attribute3.setDefault(div);
      throw new Error();
    } catch (AssertionError e) {
      // Expected -- no default value for attribute3
    }
  }

  @Before
  public void setUp() throws Exception {
    System.out.println("gwtSetUp");
    div = createDiv();
    div.setAttribute("id", "test1");
    Document.get().getBody().appendChild(div);
    attribute1 = new AriaValueAttribute<>("attr1", "vertical");
    attribute2 = new PrimitiveValueAttribute<>("attr2", "true");
    attribute3 = new PrimitiveValueAttribute<>("attr3");
    attribute4 = new AriaValueAttribute<>("attr4", "additions text");
    attribute5 = new AriaValueAttribute<>("attr5", "");
  }

  @After
  public void teardown() {
    System.out.println("gwtTeardown");
    div.getParentElement().removeChild(div);
  }
}

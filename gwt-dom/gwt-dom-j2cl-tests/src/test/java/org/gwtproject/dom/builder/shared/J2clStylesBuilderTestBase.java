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
package org.gwtproject.dom.builder.shared;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.gwtproject.dom.client.Element;
import org.junit.Test;

/** Base tests for {@link StylesBuilder}. */
public abstract class J2clStylesBuilderTestBase {

  @Test
  public void testTrustedProperty() {
    ElementBuilderFactory factory = getElementBuilderFactory();
    DivBuilder div = factory.createDivBuilder();

    StylesBuilder styles = div.style();
    styles.trustedProperty("color", "red"); // simple name.
    styles.trustedProperty("backgroundColor", "blue"); // camelCase name.
    styles.trustedProperty("border-color", "black"); // hyphenated name.
    styles.endStyle();

    Element elem = div.finish();
    assertEquals("red", elem.getStyle().getColor());
    assertEquals("blue", elem.getStyle().getBackgroundColor());

    /*
     * Some browsers return "black black black black" for the for sides of the
     * border.
     */
    assertTrue(elem.getStyle().getBorderColor().contains("black"));
  }

  /** Get the element builder factory used to create the implementation. */
  protected abstract ElementBuilderFactory getElementBuilderFactory();
}
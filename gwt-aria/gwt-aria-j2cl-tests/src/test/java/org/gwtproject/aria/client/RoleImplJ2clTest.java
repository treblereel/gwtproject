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
import static junit.framework.TestCase.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.dom.client.AnchorElement;
import org.gwtproject.dom.client.DivElement;
import org.gwtproject.dom.client.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@J2clTestInput(RoleImplJ2clTest.class)
public class RoleImplJ2clTest {

  private DivElement div;
  private RegionRole regionRole;

  @Test
  public void testSetGetRemoveRole() {
    assertEquals(null, Roles.roleOf(div));
    regionRole.set(div);
    assertEquals(regionRole, Roles.roleOf(div));
    regionRole.remove(div);
    assertEquals(null, Roles.roleOf(div));
    div.setAttribute("role", "region fallback1 fallback2");
    assertEquals(regionRole, Roles.roleOf(div));
    div.setAttribute("role", "fallback1 region fallback2");
    assertEquals(regionRole, Roles.roleOf(div));
    div.setAttribute("role", "fallback1 fallback2 fallback3");
    assertEquals(null, Roles.roleOf(div));
  }

  @Test
  public void testSetGetRemoveProperty() {
    assertEquals("", regionRole.getAriaLabelledbyProperty(div));
    regionRole.setAriaLabelledbyProperty(div, Id.of("test1"));
    assertEquals("test1", regionRole.getAriaLabelledbyProperty(div));
    regionRole.removeAriaLabelledbyProperty(div);
    assertEquals("", regionRole.getAriaLabelledbyProperty(div));
  }

  @Test
  public void testSetGetRemoveNmtokensProperty() {
    ButtonRole buttonRole = Roles.getButtonRole();
    assertEquals("", buttonRole.getAriaDropeffectProperty(div));
    regionRole.setAriaDropeffectProperty(
        div, org.gwtproject.aria.client.DropeffectValue.COPY, DropeffectValue.MOVE);
    assertEquals("copy move", regionRole.getAriaDropeffectProperty(div));
    regionRole.removeAriaDropeffectProperty(div);
    assertEquals("", regionRole.getAriaDropeffectProperty(div));
  }

  @Test
  public void testSetGetRemoveState() {
    assertEquals("", regionRole.getAriaInvalidState(div));
    regionRole.setAriaInvalidState(div, org.gwtproject.aria.client.InvalidValue.GRAMMAR);
    assertEquals(InvalidValue.GRAMMAR.getAriaValue(), regionRole.getAriaInvalidState(div));
    regionRole.removeAriaInvalidState(div);
    assertEquals("", regionRole.getAriaInvalidState(div));
  }

  @Test
  public void testSetGetRemoveExtraAttributes() {
    // Older versions of IE do not support tabIndex on divs, so use an anchor
    // element instead.
    AnchorElement anchor = createAnchor();
    Document.get().getBody().appendChild(anchor);

    // Some versions of IE default to "0" instead of ""
    assertTrue(
        "".equals(regionRole.getTabindexExtraAttribute(div))
            || "0".equals(regionRole.getTabindexExtraAttribute(div)));
    regionRole.setTabindexExtraAttribute(anchor, 1);
    assertEquals("1", regionRole.getTabindexExtraAttribute(anchor));
    regionRole.removeTabindexExtraAttribute(anchor);
    // Some versions of IE default to "0" instead of ""
    assertTrue(
        "".equals(regionRole.getTabindexExtraAttribute(div))
            || "0".equals(regionRole.getTabindexExtraAttribute(div)));

    anchor.getParentElement().removeChild(anchor);
  }

  private AnchorElement createAnchor() {
    return Document.get().createAnchorElement();
  }

  @Before
  public void setUp() throws Exception {
    div = Document.get().createDivElement();
    div.setAttribute("id", "test1");
    Document.get().getBody().appendChild(div);
    regionRole = Roles.getRegionRole();
  }

  @After
  public void tearDown() throws Exception {
    div.getParentElement().removeChild(div);
  }
}

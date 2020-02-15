/*
 * Copyright © 2018 The GWT Authors
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
package org.gwtproject.i18n.client;

import com.google.gwt.junit.client.GWTTestCase;
import elemental2.core.JsObject;
import elemental2.dom.DomGlobal;
import java.util.Collection;
import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.Set;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

public class DictionaryTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "org.gwtproject.i18n.i18n";
  }

  public void testDictionary() {
    createDummyDictionaries();
    Dictionary d = Dictionary.getDictionary("testDic");
    assertEquals("3 {2},{2},{2}, one {0}, two {1} {1}", d.get("formattedMessage"));
    assertEquals("4", d.get("d"));
    Set<String> s = d.keySet();
    assertTrue(s.contains("a"));
    assertTrue(s.contains("b"));
    assertFalse(s.contains("c"));
    Collection<String> s2 = d.values();
    assertTrue(s2.contains("A"));
    assertTrue(s2.contains("B"));
    Iterator<String> iter = s2.iterator();
    assertEquals("3 {2},{2},{2}, one {0}, two {1} {1}", iter.next());
    assertEquals(4, s2.size());
    Dictionary empty = Dictionary.getDictionary("emptyDic");
    assertEquals(0, empty.keySet().size());
    boolean threwError = false;
    try {
      Dictionary.getDictionary("malformedDic");
    } catch (MissingResourceException e) {
      threwError = true;
    }
    assertTrue(threwError);
  }

  private void createDummyDictionaries() {

    JsObject dict = JsObject.create(JsPropertyMap.of());
    JsObject emptyDict = JsObject.create(JsPropertyMap.of());
    JsPropertyMap<JsObject> window = Js.cast(DomGlobal.window);
    window.set("testDic", dict);
    window.set("emptyDic", emptyDict);

    JsPropertyMap<Integer> windowMap2 = Js.cast(DomGlobal.window);
    windowMap2.set("malformedDic", 4);

    JsPropertyMap<String> dictMap = Js.cast(dict);

    dictMap.set("formattedMessage", "3 {2},{2},{2}, one {0}, two {1} {1}");
    dictMap.set("a", "A");
    dictMap.set("b", "B");
    dictMap.set("d", "4");
  }
}

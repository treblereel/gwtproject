/*
 * Copyright 2018 Google Inc.
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
package org.gwtproject.uibinder.processor.elementparsers;

import javax.lang.model.type.TypeMirror;
import org.gwtproject.uibinder.processor.UiBinderWriter;
import org.gwtproject.uibinder.processor.XMLElement;
import org.gwtproject.uibinder.processor.ext.UnableToCompleteException;

/** A parser for ListBox items. */
public class ListBoxParser implements ElementParser {

  private static final String ITEM_TAG = "item";

  public void parse(XMLElement elem, String fieldName, TypeMirror type, UiBinderWriter writer)
      throws UnableToCompleteException {
    // Parse children.
    for (XMLElement child : elem.consumeChildElements()) {
      String tagName = child.getLocalName();
      if (!tagName.equals(ITEM_TAG)) {
        writer.die(elem, "Invalid ListBox child element: " + tagName);
      }
      String value = child.consumeStringAttribute("value");
      String innerText = child.consumeInnerTextEscapedAsStringLiteral(new TextInterpreter(writer));
      if (value != null) {
        writer.addStatement("%s.addItem(\"%s\", %s);", fieldName, innerText, value);
      } else {
        writer.addStatement("%s.addItem(\"%s\");", fieldName, innerText);
      }
    }
  }
}

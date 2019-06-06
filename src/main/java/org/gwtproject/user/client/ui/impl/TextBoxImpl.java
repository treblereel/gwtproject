/*
 * Copyright 2006 Google Inc.
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
package org.gwtproject.user.client.ui.impl;

import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.dom.client.Element;
import org.gwtproject.user.client.ui.ValueBoxBase;

/**
 * Implementation class used by {@link org.gwtproject.user.client.ui.TextBox}.
 */
public class TextBoxImpl {

  public int getCursorPos(Element elem) {
    JsPropertyMap jsObject = ((JsPropertyMap)elem);
    if(jsObject.has("selectionStart")) {
        try {
          return Integer.valueOf(jsObject.get("selectionStart").toString());
        } catch (Exception e) {

        }
    }
    return 0;
  }

  public int getSelectionLength(Element elem) {
    JsPropertyMap jsObject = ((JsPropertyMap)elem);
    if(jsObject.has("selectionEnd") && jsObject.has("selectionStart")) {
      try {
        int selectionEnd = Integer.valueOf(jsObject.get("selectionEnd").toString());
        int selectionStart = Integer.valueOf(jsObject.get("selectionStart").toString());
        return selectionEnd - selectionStart;
      } catch (Exception e) {

      }
    }
    return 0;
  }

  public int getTextAreaCursorPos(Element elem) {
    return getCursorPos(elem);
  }

  public int getTextAreaSelectionLength(Element elem) {
    return getSelectionLength(elem);
  }

  public void setSelectionRange(Element elem, int pos, int length) {
    try {
      ((ValueBoxBase) Js.uncheckedCast(elem)).setSelectionRange(pos, pos + length);
    } catch (Exception e) {

    }
  }
}

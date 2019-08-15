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
package org.gwtproject.user.client.ui.impl;

import elemental2.dom.CSSStyleDeclaration;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import jsinterop.annotations.JsFunction;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.JavaScriptObject;
import org.gwtproject.dom.client.DivElement;
import org.gwtproject.dom.client.Document;
import org.gwtproject.dom.client.Element;

/**
 * Implementation of {@link FocusImpl} that
 * uses a hidden input element to serve as a 'proxy' for accesskeys, which are
 * only supported on form elements in most browsers.
 */
public class FocusImplStandard extends FocusImpl {

  /**
   * Single focusHandler shared by all focusable.
   */
  static JavaScriptObject focusHandler;

  private static Element createFocusable0(JavaScriptObject focusHandler) {
    // Divs are focusable in all browsers, but only IE supports the accessKey
    // property on divs. We use the infamous 'hidden input' trick to add an
    // accessKey to the focusable div. Note that the input is only used to
    // capture focus when the accessKey is pressed. Focus is forwarded to the
    // div immediately.
    DivElement div = Document.get().createDivElement();
    div.setTabIndex(0);

    HTMLInputElement input = (HTMLInputElement) DomGlobal.document.createElement("input");

    input.type = "text";
    input.tabIndex = -1;
    input.setAttribute("aria-hidden", "true");
    CSSStyleDeclaration style = input.style;
    style.opacity = Js.cast(0);
    style.height = Js.cast("1px");
    style.width = Js.cast("1px");
    style.zIndex = Js.cast(-1);
    style.overflow = "hidden";
    style.position = "absolute";

    // Note that we're using isolated lambda methods as the event listeners
    // to avoid creating a memory leaks. (Lambdas here would create cycles
    // involving the div and input).  This also allows us to share a single
    // set of handlers among every focusable item.
    input.addEventListener("focus", Js.uncheckedCast(focusHandler), false);
    div.appendChild(Js.uncheckedCast(input));
    return div;
  }

  @Override
  public Element createFocusable() {
    return createFocusable0(ensureFocusHandler());
  }

  @Override
  public void setAccessKey(Element elem, char key) {
    ((JsPropertyMap)elem.getFirstChild()).set("accessKey", Character.toString(key));
  }

  /**
   * Use an isolated method call to create the handler to avoid creating memory
   * leaks via handler-closures-element.
   */
  private JavaScriptObject createFocusHandler() {
            // This function is called directly as an event handler, so 'this' is
            // set up by the browser to be the input on which the event is fired. We
            // call focus() in a timeout or the element may be blurred when this event
            // ends.
    FnVarArgs func =  (event) -> {
      Element _this = Js.uncheckedCast(this);
      HTMLDivElement div = Js.uncheckedCast(_this.getParentNode());
      if (div.onfocus != null) {
        DomGlobal.setTimeout(p0 -> div.focus(), 0);
      }
    };
    return (JavaScriptObject)func;
  }

  private JavaScriptObject ensureFocusHandler() {
    return focusHandler != null ? focusHandler : (focusHandler = createFocusHandler());
  }

  @FunctionalInterface
  @JsFunction
  interface FnVarArgs {
    void onInvoke(Object... arg1);
  }
}

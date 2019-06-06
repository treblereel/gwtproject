/*
 * Copyright 2008 Google Inc.
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

import elemental2.dom.HTMLFormElement;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.dom.client.Element;
import org.gwtproject.dom.client.IFrameElement;
import org.gwtproject.user.client.impl.DOMImplStandard;

/**
 * Implementation class used by {@link org.gwtproject.user.client.ui.FormPanel}.
 */
public class FormPanelImpl {

  /**
   * Gets the response html from the loaded iframe.
   *
   * @param iframe the iframe from which the response html is to be extracted
   * @return the response html
   */
  public String getContents(Element iframe) {
      try {
          // Make sure the iframe's window & document are loaded.
          IFrameElement _iframe = Js.uncheckedCast(iframe);
          if (_iframe.getContentDocument() == null) {
              return null;
          }
          // Get the body's entire inner HTML.
          return _iframe.getContentDocument().getBody().getInnerHTML();
      } catch (Exception e) {
          return null;
      }
  }

  /**
   * Gets the form element's encoding.
   *
   * @param form the form whose encoding is to be retrieved
   * @return the form's encoding type
   */
  public String getEncoding(Element form) {
    return ((JsPropertyMap)form).get("enctype").toString();
  }

  /**
   * Hooks the iframe's onLoad event and the form's onSubmit event.
   *
   * @param iframe the iframe whose onLoad event is to be hooked
   * @param form the form whose onSubmit event is to be hooked
   * @param listener the listener to receive notification
   */
  public void hookEvents(Element iframe, Element form,
      FormPanelImplHost listener) {
        if (iframe != null) {
          ((JsPropertyMap)iframe).set("onload", (DOMImplStandard.Fn) event -> {
            if(!((JsPropertyMap)iframe).has("__formAction")){
              return;
            }
            listener.onFrameLoad();
          });
        }
  }

  /**
   * Resets a form.
   *
   * @param form the form to be reset
   */
  public void reset(Element form) {
    ((HTMLFormElement)Js.uncheckedCast(form)).reset();
  }

  /**
   * Sets the form element's encoding.
   *
   * @param form the form whose encoding is to be set
   * @param encoding the new encoding type
   */
  public void setEncoding(Element form, String encoding) {
    ((JsPropertyMap)form).set("enctype", encoding);
    ((JsPropertyMap)form).set("encoding", encoding);
  }

  /**
   * Submits a form.
   *
   * @param form the form to be submitted
   * @param iframe the iframe that is targetted, or <code>null</code>
   */
  public void submit(Element form, Element iframe) {
    JsPropertyMap map = ((JsPropertyMap)form);
    if(iframe != null) {
      ((JsPropertyMap)iframe).set("__formAction", map.get("action"));
    } else {
      ((HTMLFormElement)Js.uncheckedCast(form)).submit();
    }
  }

  /**
   * Unhooks the iframe's onLoad event.
   *
   * @param iframe the iframe whose onLoad event is to be unhooked
   * @param form the form whose onSubmit event is to be unhooked
   */
  public void unhookEvents(Element iframe, Element form) {
      if(iframe != null) {
          ((JsPropertyMap)form).set("onload", null);
          ((JsPropertyMap)form).set("onsubmit", null);
      }
  }

}

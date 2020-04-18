/*
 * Copyright 2016 Google Inc.
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

package org.gwtproject.xml.client.impl;

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import org.gwtproject.xml.client.impl.DocumentImpl.NativeDocumentImpl;
import org.gwtproject.xml.client.impl.XMLParserImpl.XMLParserImplIE8And9.NativeInternalDocumentImpl;

class JsHelper {

    /**
     * Called from JSNI to select a DOM document; this is necessary due to
     * different versions of IE and Windows having different available DOM
     * implementations.
     */
    public static NativeInternalDocumentImpl selectDOMDocumentVersion() {
        try {
            return new ActiveXObject("Msxml2.DOMDocument");
        } catch (Exception e) {
        }
        try {
            return new ActiveXObject("MSXML.DOMDocument");
        } catch (Exception e) {
        }
        try {
            return new ActiveXObject("MSXML3.DOMDocument");
        } catch (Exception e) {
        }
        try {
            return new ActiveXObject("Microsoft.XmlDom");
        } catch (Exception e) {
        }
        try {
            return new ActiveXObject("Microsoft.DOMDocument");
        } catch (Exception e) {
        }

        throw new Error("XMLParserImplIE6.createDocumentImpl: "
                                + "Could not find appropriate version of DOMDocument.");
    }

    public static NativeDocumentImpl createDocumentImpl() {
        return Js.uncheckedCast(DomGlobal.document.implementation.createDocument("", "", null));
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL)
    private static class ActiveXObject extends NativeInternalDocumentImpl {

        @JsConstructor
        public ActiveXObject(String value) {
        }
    }
}


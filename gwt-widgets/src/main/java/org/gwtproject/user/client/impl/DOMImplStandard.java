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
package org.gwtproject.user.client.impl;

import elemental2.dom.DomGlobal;
import elemental2.dom.EventTarget;
import jsinterop.annotations.JsFunction;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.JavaScriptObject;
import org.gwtproject.dom.client.BrowserEvents;
import org.gwtproject.dom.client.Element;
import org.gwtproject.dom.client.Node;
import org.gwtproject.user.client.DOM;
import org.gwtproject.user.client.Event;

/**
 * Base implementation of {@link org.gwtproject.user.client.impl.DOMImpl} shared
 * by those browsers that come a bit closer to supporting a common standard (ie,
 * not legacy IEs).
 */
public abstract class DOMImplStandard extends DOMImpl {

    private static Element captureElem;
    private static EventMap bitlessEventDispatchers = getBitlessEventDispatchers();
    private static EventMap captureEventDispatchers = getCaptureEventDispatchers();

    /**
     * Adds custom bitless event dispatchers to GWT. If no specific event dispatcher supplied for an
     * event, the default dispatcher is used.
     * <p> Example usage:
     * <pre>
     * static {
     *   DOMImplStandard.addBitlessEventDispatchers(getMyCustomDispatchers());
     * }
     *
     * private static native JavaScriptObject getMyCustomDispatchers() /*-{
     *   return {
     *     click: @com.xxx.YYY::myCustomDispatcher(*),
     *     ...
     *   };
     * }-* /;
     * </pre>
     *
     * <p> Note that although this method is public for extensions, it is subject to change in
     * different releases.
     * @param eventMap an object that provides dispatching methods keyed with the name of the event
     */
    public static void addBitlessEventDispatchers(JavaScriptObject eventMap) {
        ensureInit();
        bitlessEventDispatchers.merge(eventMap);
    }

    /**
     * Adds custom capture event dispatchers to GWT.
     * <p> Example usage:
     * <pre>
     * static {
     *   if (isIE10Plus())) {
     *     DOMImplStandard.addCaptureEventDispatchers(getMsPointerCaptureDispatchers());
     *   }
     * }
     *
     * private static native JavaScriptObject getMsPointerCaptureDispatchers() /*-{
     *   return {
     *     MSPointerDown: @org.gwtproject.user.client.impl.DOMImplStandard::dispatchCapturedMouseEvent(*),
     *     MSPointerUp:   @org.gwtproject.user.client.impl.DOMImplStandard::dispatchCapturedMouseEvent(*),
     *     ...
     *   };
     * }-* /;
     * </pre>
     *
     * <p> Note that although this method is public for extensions, it is subject to change in
     * different releases.
     * @param eventMap an object that provides dispatching methods keyed with the name of the event
     */
    public static void addCaptureEventDispatchers(JavaScriptObject eventMap) {
        ensureInit();
        captureEventDispatchers.merge(eventMap);
    }

    private static void ensureInit() {
        if (eventSystemIsInitialized) {
            throw new IllegalStateException("Event system already initialized");
        }

        // Ensure that any default extensions for the browser is registered via
        // static initializers in deferred binding of DOMImpl:
        new DOMImplStandardBase();
    }

    public static void dispatchEvent(Event evt) {
        Element element = getFirstAncestorWithListener(evt);
        if (element == null) {
            return;
        }
        DOM.dispatchEvent(evt, element.getNodeType() != 1 ? null : element, getEventListener(element));
    }

    private static Element getFirstAncestorWithListener(Event evt) {
        Element curElem = evt.getCurrentEventTarget().cast();
        while (curElem != null && getEventListener(curElem) == null) {
            curElem = curElem.getParentNode().cast();
        }
        return curElem;
    }

    private static void dispatchDragEvent(Event evt) {
        // Some drag events must call preventDefault to prevent native text selection.
        evt.preventDefault();
        dispatchEvent(evt);
    }

    private static void dispatchUnhandledEvent(Event evt) {
        Element element = evt.getCurrentEventTarget().cast();
        element.setPropertyString("__gwtLastUnhandledEvent", evt.getType());
        dispatchEvent(evt);
    }

    private static void dispatchCapturedEvent(Event evt) {
        DOM.previewEvent(evt);
    }

    private static void dispatchCapturedMouseEvent(Event evt) {
        boolean cancelled = !DOM.previewEvent(evt);
        if (cancelled || captureElem == null) {
            return;
        }
        if (DOM.dispatchEvent(evt, captureElem)) {
            evt.stopPropagation();
        }
    }

    private static EventMap getBitlessEventDispatchers() {
        EventMap eventMap = new EventMap();
        JsPropertyMap map = Js.uncheckedCast(eventMap);
        map.set("_default_", (Fn) (event) -> dispatchEvent(event));
        map.set("dragenter", (Fn) (event) -> dispatchDragEvent(event));
        map.set("dragover", (Fn) (event) -> dispatchDragEvent(event));
        return eventMap;
    }

    private static EventMap getCaptureEventDispatchers() {
        EventMap eventMap = new EventMap();
        JsPropertyMap map = Js.uncheckedCast(eventMap);
        // Mouse events
        map.set("click", (Fn) (event) ->      dispatchCapturedMouseEvent(event));
        map.set("dblclick", (Fn) (event) ->   dispatchCapturedMouseEvent(event));
        map.set("mousedown", (Fn) (event) ->  dispatchCapturedMouseEvent(event));
        map.set("mouseup", (Fn) (event) ->    dispatchCapturedMouseEvent(event));
        map.set("mousemove", (Fn) (event) ->  dispatchCapturedMouseEvent(event));
        map.set("mouseover", (Fn) (event) ->  dispatchCapturedMouseEvent(event));
        map.set("mouseout", (Fn) (event) ->   dispatchCapturedMouseEvent(event));
        map.set("mousewheel", (Fn) (event) -> dispatchCapturedMouseEvent(event));
        // Keyboard events
        map.set("keydown", (Fn) (event) ->    dispatchCapturedEvent(event));
        map.set("keyup", (Fn) (event) ->      dispatchCapturedEvent(event));
        map.set("keypress", (Fn) (event) ->   dispatchCapturedEvent(event));
        // Touch events
        map.set("touchstart", (Fn) (event) ->    dispatchCapturedMouseEvent(event));
        map.set("touchend", (Fn) (event) ->      dispatchCapturedMouseEvent(event));
        map.set("touchmove", (Fn) (event) ->     dispatchCapturedMouseEvent(event));
        map.set("touchcancel", (Fn) (event) ->   dispatchCapturedMouseEvent(event));
        map.set("gesturestart", (Fn) (event) ->  dispatchCapturedMouseEvent(event));
        map.set("gestureend", (Fn) (event) ->    dispatchCapturedMouseEvent(event));
        map.set("gesturechange", (Fn) (event) -> dispatchCapturedMouseEvent(event));
        return eventMap;
    }

    @Override
    public Element eventGetFromElement(Event evt) {
        if (evt.getType().equals(BrowserEvents.MOUSEOVER)) {
            return evt.getRelatedEventTarget().cast();
        }
        if (evt.getType().equals(BrowserEvents.MOUSEOUT)) {
            return evt.getEventTarget().cast();
        }
        return null;
    }

    @Override
    public Element eventGetToElement(Event evt) {
        if (evt.getType().equals(BrowserEvents.MOUSEOVER)) {
            return evt.getEventTarget().cast();
        }

        if (evt.getType().equals(BrowserEvents.MOUSEOUT)) {
            return evt.getRelatedEventTarget().cast();
        }

        return null;
    }

    @Override
    public Element getChild(Element elem, int index) {
        int count = 0;
        Node child = elem.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == 1) {
                if (index == count) {
                    return (Element) child;
                }
                ++count;
            }
            child = child.getNextSibling();
        }
        return null;
    }

    @Override
    public int getChildCount(Element elem) {
        int count = 0;
        Node child = elem.getFirstChild();
        while (child != null) {
            if (child.getNodeType() == 1) {
                ++count;
            }
            child = child.getNextSibling();
        }
        return count;
    }

    @Override
    public int getChildIndex(Element parent, Element toFind) {
        int count = 0;
        Node child = parent.getFirstChild();
        while (child != null) {
            if (child == toFind) {
                return count;
            }
            if (child.getNodeType() == 1) {
                ++count;
            }

            child = child.getNextSibling();
        }
        return -1;
    }

    @Override
    public void insertChild(Element parent, Element toAdd, int index) {
        int count = 0;
        Node child = parent.getFirstChild();
        Node before = null;
        while (child != null) {
            if (child.getNodeType() == 1) {
                if (count == index) {
                    before = child;
                    break;
                }
                ++count;
            }
            child = child.getNextSibling();
        }
        parent.insertBefore(toAdd, before);
    }

    @Override
    public void releaseCapture(Element elem) {
        maybeInitializeEventSystem();
        if (captureElem == elem) {
            captureElem = null;
        }
    }

    @Override
    public void setCapture(Element elem) {
        maybeInitializeEventSystem();
        captureElem = elem;
    }

    @Override
    public void sinkBitlessEvent(Element elem, String eventTypeName) {
        maybeInitializeEventSystem();
        sinkBitlessEventImpl(elem, eventTypeName);
    }

    @Override
    public void sinkEvents(Element elem, int bits) {
        maybeInitializeEventSystem();
        sinkEventsImpl(elem, bits);
    }

    @Override
    protected void initEventSystem() {
        EventMap.foreach(captureEventDispatchers, (elm, value) -> DomGlobal.window.addEventListener(elm, Js.uncheckedCast(value), true));
    }

    protected void sinkBitlessEventImpl(Element elem, String eventTypeName) {
        JsPropertyMap map = Js.asPropertyMap(bitlessEventDispatchers);
        Object dispatcher;
        if (map.has(eventTypeName)) {
            dispatcher = map.get(eventTypeName);
        } else {
            dispatcher = map.get("_default_");
        }


        ((EventTarget) Js.uncheckedCast(elem))
                .addEventListener(eventTypeName, Js.uncheckedCast(dispatcher), false);
    }

    protected void sinkEventsImpl(Element elem, int bits) {
        JsPropertyMap map = Js.asPropertyMap(elem);
        int chMask = (map.has("__eventBits") ? Integer.valueOf(map.get("__eventBits").toString()) : 0) ^ bits;
        map.set("__eventBits", bits);

        if ((chMask & 0x00001) != 0) {
            map.set("onclick", ((bits & 0x00001) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00002) != 0) {
            map.set("ondblclick", ((bits & 0x00002) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00004) != 0) {
            map.set("onmousedown", ((bits & 0x00004) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00008) != 0) {
            map.set("onmouseup", ((bits & 0x00008) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00010) != 0) {
            map.set("onmouseover", ((bits & 0x00010) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00020) != 0) {
            map.set("onmouseout", ((bits & 0x00020) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00040) != 0) {
            map.set("onmousemove", ((bits & 0x00040) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00080) != 0) {
            map.set("onkeydown", ((bits & 0x00080) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00100) != 0) {
            map.set("onkeypress", ((bits & 0x00100) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00200) != 0) {
            map.set("onkeyup", ((bits & 0x00200) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00400) != 0) {
            map.set("onchange", ((bits & 0x00400) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x00800) != 0) {
            map.set("onfocus", ((bits & 0x00400) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x01000) != 0) {
            map.set("onblur", ((bits & 0x01000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x02000) != 0) {
            map.set("onlosecapture", ((bits & 0x02000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x02000) != 0) {
            map.set("onlosecapture", ((bits & 0x02000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x04000) != 0) {
            map.set("onscroll", ((bits & 0x04000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x08000) != 0) {
            map.set("onload", ((bits & 0x08000) != 0) ? (Fn) event -> dispatchUnhandledEvent(event) : null);
        }

        if ((chMask & 0x10000) != 0) {
            map.set("onerror", ((bits & 0x10000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x20000) != 0) {
            map.set("onmousewheel", ((bits & 0x20000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x40000) != 0) {
            map.set("oncontextmenu", ((bits & 0x40000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x80000) != 0) {
            map.set("onpaste", ((bits & 0x80000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x100000) != 0) {
            map.set("ontouchstart", ((bits & 0x100000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x200000) != 0) {
            map.set("ontouchmove", ((bits & 0x200000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x400000) != 0) {
            map.set("ontouchend", ((bits & 0x400000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x800000) != 0) {
            map.set("ontouchcancel", ((bits & 0x800000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x1000000) != 0) {
            map.set("ongesturestart", ((bits & 0x1000000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x2000000) != 0) {
            map.set("ongesturechange", ((bits & 0x2000000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

        if ((chMask & 0x4000000) != 0) {
            map.set("ongestureend", ((bits & 0x4000000) != 0) ? (Fn) event -> dispatchEvent(event) : null);
        }

    }

    @FunctionalInterface
    @JsFunction
    public interface Fn {

        void onInvoke(Event evt);
    }
}

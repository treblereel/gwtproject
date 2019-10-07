/*
 * Copyright 2013 Google Inc.
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
package org.gwtproject.user.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.dom.Event;
import jsinterop.annotations.JsFunction;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.JavaScriptObject;
import org.gwtproject.dom.client.Element;
import org.gwtproject.dom.client.NativeEvent;
import org.gwtproject.event.dom.client.DomEvent;
import org.gwtproject.event.legacy.shared.EventHandler;
import org.gwtproject.user.client.impl.DOMImplStandard;
import org.gwtproject.user.client.impl.DOMImplStandardBase;
import org.gwtproject.user.client.ui.Button;
import org.gwtproject.user.client.ui.RootPanel;

/**
 * Tests standard DOM operations in the {@link DOM} class.
 * <p>
 * The test has its own module so that it will always be the first test executed in the page. By
 * this way, we can make sure that the custom event registration always takes place before the event
 * system gets initialized.
 */
@J2clTestInput(CustomEventsTest.class)
public class CustomEventsTest extends GWTTestCase {

    private static boolean initCustomEventDispatch = true;
    private Button button;

    private static NativeEvent createCustomEvent(String evt) {
        elemental2.dom.Event event = new Event("Event");
        event.initEvent(evt, true, false);
        return Js.uncheckedCast(event);
    }

    private static JavaScriptObject getBitlessCustomDisptachers() {
        JsPropertyMap result = JsPropertyMap.of();
        Fn fn = event -> {
            DOMImplStandard.dispatchEvent(event);
            String title = Js.uncheckedCast(Js.asPropertyMap(Js.asPropertyMap(event).get("target")).get("title"));
            Js.asPropertyMap(Js.asPropertyMap(event).get("target")).set("title", title + "-custom_method");
        };
        result.set("c2", fn);
        return Js.uncheckedCast(result);
    }

    private static void ensureCustomEventDispatch() {
        if (initCustomEventDispatch) {
            DOMImplStandard.addBitlessEventDispatchers(getBitlessCustomDisptachers());
            initCustomEventDispatch = false;
        }
    }

    private static boolean isStandard() {
        return new DOMImplStandardBase() instanceof DOMImplStandard;
    }

    @Override
    public String getModuleName() {
        return "org.gwtproject.user.CustomEventsTest";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        ensureCustomEventDispatch();

        button = new Button();
        button.setTitle("event");
        button.addBitlessDomHandler(new CustomHandler(), CustomEvent1.TYPE);
        button.addBitlessDomHandler(new CustomHandler(), CustomEvent2.TYPE);

        RootPanel.get().add(button);
    }

    @Override
    protected void gwtTearDown() throws Exception {
        RootPanel.get().remove(button);
    }

    public void testCustomEvent() {
        if (!isStandard()) {
            return; // Custom event support is for standard browsers (i.e. no IE 6/7/8)
        }

        dispatchEvent("c1");
        assertEquals("event-dispatched", button.getTitle());
    }

    public void testCustomDispatchEvent() {
        if (!isStandard()) {
            return; // Custom event support is for standard browsers (i.e. no IE 6/7/8)
        }

        dispatchEvent("c2");
        assertEquals("event-dispatched-custom_method", button.getTitle());
    }

    private void dispatchEvent(String evt) {
        button.getElement().dispatchEvent(createCustomEvent(evt));
    }

    @FunctionalInterface
    @JsFunction
    public interface Fn {

        void onInvoke(org.gwtproject.user.client.Event event);
    }

    private static class CustomHandler implements EventHandler {

        void on(DomEvent<?> customEvent) {
            Element el = customEvent.getRelativeElement();
            el.setTitle(el.getTitle() + "-dispatched");
        }
    }

    private static class CustomEvent1 extends DomEvent<CustomHandler> {

        static final Type<CustomHandler> TYPE = new Type<CustomHandler>("c1", new CustomEvent1());

        @Override
        public Type<CustomHandler> getAssociatedType() {
            return TYPE;
        }

        @Override
        protected void dispatch(CustomHandler handler) {
            handler.on(this);
        }
    }

    private static class CustomEvent2 extends DomEvent<CustomHandler> {

        static final Type<CustomHandler> TYPE = new Type<CustomHandler>("c2", new CustomEvent2());

        @Override
        public Type<CustomHandler> getAssociatedType() {
            return TYPE;
        }

        @Override
        protected void dispatch(CustomHandler handler) {
            handler.on(this);
        }
    }
}

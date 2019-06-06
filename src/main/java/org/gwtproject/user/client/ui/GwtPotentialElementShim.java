package org.gwtproject.user.client.ui;

import elemental2.core.JsArray;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import org.gwtproject.core.client.JavaScriptObject;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 6/9/19
 */
@JsType
class GwtPotentialElementShim extends JavaScriptObject {

    public String className = "";
    public int clientHeight = 0;
    public int clientWidth = 0;
    public String tagName = "";
    public String dir = "";
    public String href = "";
    public String id = "";
    public String lang = "";
    public int nodeType = 1;
    public String src = "";
    public JsArray<String> style = new JsArray<>();
    public String title = "";
    public Fn __gwt_resolve;

    @JsConstructor
    GwtPotentialElementShim(){

    }

    public Object getAttribute(String name) {
        return Js.asPropertyMap(this).get(name);
    }

    public void removeAttribute(String name) {
        Js.asPropertyMap(this).set(name, null);
    }

    public void setAttribute(String name, String value) {
        Js.asPropertyMap(this).set(name, value);
    }

    @FunctionalInterface
    @JsFunction
    interface Fn {
        void onInvoke();
    }
}

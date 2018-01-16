package org.gwtproject.json.client;

import com.google.gwt.json.client.JSONException;
import elemental2.core.JsArray;
import elemental2.core.JsObject;
import jsinterop.base.Js;

import static java.util.Objects.nonNull;

public class JSONValueFactory {

    public static JSONValue create(Object value) {
        String type = Js.typeof(value);
        switch (type) {
            case "boolean":
                return JSONBoolean.getInstance((Boolean) value);
            case "number":
                return new JSONNumber((Double) value);
            case "string":
                return new JSONString((String) value);
            case "object":
                return createObject(value);
            case "function":
                return createObject(value);
            case "undefined":
                return null;
        }
        throw new JSONException("Unexpected typeof result '" + type + "'; please report this bug to the GWT team");
    }

    private static JSONValue createObject(Object o) {
        if (Js.isFalsy(o)) {
            return JSONNull.getInstance();
        }

        JsObject jsObject = Js.cast(o);
        Object v = nonNull(jsObject.valueOf()) ? jsObject.valueOf() : jsObject;
        if (!Js.isTripleEqual(v, jsObject)) {
            return create(v);
        } else if (JsArray.isArray(o)) {
            return new JSONArray((JsArray<Object>) Js.cast(o));
        } else {
            return new JSONObject((JsObject) Js.cast(o));
        }
    }
}

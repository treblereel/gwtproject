package org.gwtproject.json.client;

import elemental2.core.JsArray;
import elemental2.core.JsObject;
import jsinterop.base.Js;

import java.util.logging.Logger;

public class JSONValueFactory {

    private static final Logger LOGGER = Logger.getLogger(JSONValueFactory.class.getCanonicalName());

    /*
    private static JsMap<String, Function<Object, JSONValue>> initTypeMap() {
        JsMap<String, Function<Object, JSONValue>> types = new JsMap<>();
        types.set("boolean", o -> createBoolean((boolean) o));
        types.set("number", o -> createNumber((double) o));
        types.set("string", o -> createString((String) o));
        types.set("object", JSONParser::createObject);
        types.set("function", JSONParser::createObject);
        types.set("undefined", o -> createUndefined());
        return types;
    }
     */

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
        return null;
    }

    private static JSONValue createObject(Object o) {
        if (Js.isFalsy(o)) {
            return JSONNull.getInstance();
        }

        JsObject jsObject = Js.cast(o);
        Object v = Js.isTruthy(jsObject.valueOf()) ? jsObject.valueOf() : jsObject;
        if (!Js.isTripleEqual(v, jsObject)) {
            return create(v);
        } else if (JsArray.isArray(o)) {
            return new JSONArray((JsArray<Object>) Js.cast(o));
        } else {
            return new JSONObject((JsObject) Js.cast(o));
        }
    }
}

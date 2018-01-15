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
package org.gwtproject.json.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JsonUtils;
import elemental2.core.Global;
import jsinterop.annotations.JsMethod;

import java.util.logging.Logger;

/**
 * Parses the string representation of a JSON object into a set of
 * JSONValue-derived objects.
 *
 * @see com.google.gwt.json.client.JSONValue
 */
public class JSONParser {

    private static final Logger LOGGER = Logger.getLogger(JSONParser.class.getCanonicalName());

    /**
     * Evaluates a trusted JSON string and returns its JSONValue representation.
     * CAUTION! This method calls the JavaScript <code>eval()</code> function,
     * which can execute arbitrary script. DO NOT pass an untrusted string into
     * this method.
     * <p>
     * <p>
     * This method has been deprecated. Please call either
     * {@link #parseStrict(String)} (for inputs that strictly follow the JSON
     * specification) or {@link #parseLenient(String)}. The implementation of this
     * method calls parseLenient.
     *
     * @param jsonString a JSON object to parse
     * @return a JSONValue that has been built by parsing the JSON string
     * @throws NullPointerException     if <code>jsonString</code> is
     *                                  <code>null</code>
     * @throws IllegalArgumentException if <code>jsonString</code> is empty
     * @deprecated use {@link #parseStrict(String)}
     */
    @Deprecated
    public static JSONValue parse(String jsonString) {
        return parseLenient(jsonString);
    }

    /**
     * Evaluates a trusted JSON string and returns its JSONValue representation.
     * CAUTION! This method calls the JavaScript {@code eval()} function, which
     * can execute arbitrary script. DO NOT pass an untrusted string into this
     * method.
     *
     * @param jsonString a JSON object to parse
     * @return a JSONValue that has been built by parsing the JSON string
     * @throws NullPointerException     if <code>jsonString</code> is
     *                                  <code>null</code>
     * @throws IllegalArgumentException if <code>jsonString</code> is empty
     * @deprecated use {@link #parseStrict(String)}
     */
    @Deprecated
    public static JSONValue parseLenient(String jsonString) {
        return parse(jsonString, false);
    }

    /**
     * Evaluates a JSON string and returns its JSONValue representation. The
     * browser's {@code JSON.parse function} is used.
     *
     * @param jsonString a JSON object to parse
     * @return a JSONValue that has been built by parsing the JSON string
     * @throws NullPointerException     if <code>jsonString</code> is <code>null</code>
     * @throws IllegalArgumentException if <code>jsonString</code> is empty
     */
    public static JSONValue parseStrict(String jsonString) {
        return parse(jsonString, true);
    }

    static void throwJSONException(String message) {
        throw new JSONException(message);
    }

    static void throwUnknownTypeException(String typeString) {
        throw new JSONException("Unexpected typeof result '" + typeString
                + "'; please report this bug to the GWT team");
    }

    /**
     * This method converts <code>jsonString</code> into a JSONValue.
     * In strict mode (strict == true), one of two code paths is taken:
     * 1) Call JSON.parse, or
     * 2) Validate the input and call eval()
     * <p>
     * In lenient mode (strict == false), eval() is called without validation.
     *
     * @param strict if true, parse in strict mode.
     */
    private static JSONValue evaluate(String json, boolean strict) {
        Object o = null;
        if (strict) {
            try {
                o = Global.JSON.parse(json);
            } catch (Exception e) {
                throwJSONException("Error parsing JSON: " + e);
            }
        } else {
            json = JsonUtils.escapeJsonForEval(json);
            try {
                o = Global.eval('(' + json + ')');
            } catch (Exception e) {
                throwJSONException("Error parsing JSON: " + e);
            }
        }
        return JSONValueFactory.create(o);
//        String typeof = Js.typeof(o);
//        Function<Object, JSONValue> function = typeMap.get(typeof);
//        if (nonNull(function)) {
//            return function.apply(o);
//        }
//        throwUnknownTypeException(typeof);
//        return null;
        /*-{
    // Note: we cannot simply call JsonUtils.unsafeEval because it is unable
    // to return a result for inputs whose outermost type is 'string' in
    // dev mode.
    var v;
    if (strict) {
      try {
        v = JSON.parse(json);
      } catch (e) {
        return @JSONParser::throwJSONException(Ljava/lang/String;)("Error parsing JSON: " + e);
      }
    } else {
      json = @com.google.gwt.core.client.JsonUtils::escapeJsonForEval(Ljava/lang/String;)(json);
      try {
        v = eval('(' + json + ')');
      } catch (e) { 
        return @JSONParser::throwJSONException(Ljava/lang/String;)("Error parsing JSON: " + e);
      }
    }
    var func = @JSONParser::typeMap[typeof v];
    return func ? func(v) : @JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
  }-*/
    }

    private static JSONValue parse(String jsonString, boolean strict) {
        if (jsonString == null) {
            throw new NullPointerException();
        }
        if (jsonString.length() == 0) {
            throw new IllegalArgumentException("empty argument");
        }
        try {
            return evaluate(jsonString, strict);
        } catch (JavaScriptException ex) {
            throw new JSONException(ex);
        }
    }

    /**
     * Not instantiable.
     */
    private JSONParser() {
    }

    @JsMethod(name = "parse", namespace = "JSON")
    private static native Object parseJson(String text);
}

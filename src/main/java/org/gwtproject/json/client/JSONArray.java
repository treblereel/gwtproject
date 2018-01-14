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

import elemental2.core.JsArray;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Represents an array of {@link com.google.gwt.json.client.JSONValue} objects.
 */
public class JSONArray extends JSONValue {

    private static final Logger LOGGER = Logger.getLogger(JSONArray.class.getCanonicalName());

    /**
     * Called from {@link #getUnwrapper()}.
     */
    private static JsArray<JSONValue> unwrap(JSONArray value) {
        return value.jsArray;
    }

    private final JsArray<JSONValue> jsArray;

    /**
     * Creates an empty JSONArray.
     */
    public JSONArray() {
        jsArray = new JsArray<>();
    }

    /**
     * Creates a new JSONArray from the supplied JavaScriptObject representing a
     * JavaScript array.
     *
     * @param arr a JavaScript array
     */
    public JSONArray(JsArray<JSONValue> arr) {
        jsArray = arr;
    }

    /**
     * Returns <code>true</code> if <code>other</code> is a {@link JSONArray}
     * wrapping the same underlying object.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof JSONArray)) {
            return false;
        }
        return jsArray.equals(((JSONArray) other).jsArray);
    }

    /**
     * Returns the value at the specified index position.
     *
     * @param index the index of the array item to retrieve
     * @return the value at this index, or <code>null</code> if this index is
     * empty
     */
    public JSONValue get(int index) {
        return jsArray.getAt(index);
    } /*-{
    var v = this.@JSONArray::jsArray[index];
    var func = @JSONParser::typeMap[typeof v];
    return func ? func(v) : @JSONParser::throwUnknownTypeException(Ljava/lang/String;)(typeof v);
  }-*/

    @Override
    public int hashCode() {
        return jsArray.hashCode();
    }

    /**
     * Returns <code>this</code>, as this is a JSONArray.
     */
    @Override
    public JSONArray isArray() {
        return this;
    }

    /**
     * Sets the specified index to the given value.
     *
     * @param index the index to set
     * @param value the value to set
     * @return the previous value at this index, or <code>null</code> if this
     * index was empty
     */
    public JSONValue set(int index, JSONValue value) {
        JSONValue previous = get(index);
        set0(index, value);
        return previous;
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return size of this array
     */
    public int size() {
        return jsArray.length;
    } /*-{
    return this.@JSONArray::jsArray.length;
  }-*/

    ;

    /**
     * Create the JSON encoded string representation of this JSONArray instance.
     * This method may take a long time to execute if the underlying array is
     * large.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0, c = size(); i < c; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(get(i));
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    Object getUnwrapper() {
        return unwrap(this);
    } /*-{
    return @JSONArray::unwrap(Lcom/progressoft/brix/domino/json/client/JSONArray;);
  }-*/

    ;

    private void set0(int index, JSONValue value) {
        if (Objects.isNull(value))
            value = JSONNull.getInstance();
        jsArray.setAt(index, value);
    } /*-{
    if (value) {
      var func = value.@JSONValue::getUnwrapper()();
      value = func(value);
    } else {
      // Coerce Java null to undefined; there's a JSONNull for null.
      value = undefined;
    }
    this.@JSONArray::jsArray[index] = value;
  }-*/

    ;
}

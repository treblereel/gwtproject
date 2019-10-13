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
import jsinterop.base.Js;
import org.gwtproject.core.client.JavaScriptObject;

import java.util.Objects;

/**
 * Represents an array of {@link org.gwtproject.json.client.JSONValue} objects.
 */
public class JSONArray extends JSONValue {

    private final JsArray<Object> jsArray;

    /**
     * Creates an empty JSONArray.
     */
    public JSONArray() {
        jsArray = new JsArray<>();
    }

    /**
     * Creates a new JSONArray from the supplied JavaScriptObject representing a
     * JavaScript array.
     * <p>
     * use {@link #JSONArray(JsArray)} instead
     *
     * @param arr a JavaScript array
     */
    @Deprecated
    public JSONArray(JavaScriptObject arr) {
        this((JsArray<Object>) Js.cast(arr));
    }

    /**
     * Creates a new JSONArray from the supplied JavaScriptObject representing a
     * JavaScript array.
     *
     * @param arr a JavaScript array
     */
    public JSONArray(JsArray<Object> arr) {
        jsArray = arr;
    }

    /**
     * Returns the underlying JavaScript array that this object wraps.
     * <p>
     * use {@link #getArray()} instead
     */
    @Deprecated
    public JavaScriptObject getJavaScriptObject() {
        return Js.cast(jsArray);
    }

    /**
     * Returns the underlying JavaScript array that this object wraps.
     * <p>
     */
    public JsArray<Object> getArray() {
        return Js.cast(jsArray);
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
        return JSONValueFactory.create(jsArray.getAt(index));
    }

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
    }

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
        return jsArray;
    }

    private void set0(int index, JSONValue value) {
        Object unwrappedValue;
        if (Objects.isNull(value))
            unwrappedValue = Js.undefined();
        else
            unwrappedValue = value.getUnwrapper();
        jsArray.setAt(index, unwrappedValue);
    }
}

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

package org.gwtproject.i18n.client;

import elemental2.core.JsArray;
import elemental2.core.JsObject;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.i18n.client.impl.CurrencyDataImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Generated class containing all the CurrencyImpl instances.  This is just
 * the fallback in case the I18N module is not included.
 */
public class CurrencyList implements Iterable<CurrencyData> {

    /**
     * Inner class to avoid CurrencyList.clinit calls and allow this to be
     * completely removed from the generated code if instance isn't referenced
     * (such as when all you call is CurrencyList.get().getDefault() ).
     */
    private static class CurrencyListInstance {
        //TODO Create the instance using the APT generated factory
//        private static CurrencyList instance = GWT.create(CurrencyList.class);
        private static CurrencyList instance = null;
    }

    /**
     * Return the singleton instance of CurrencyList.
     */
    public static CurrencyList get() {
        return CurrencyListInstance.instance;
    }

    /**
     * Add all entries in {@code override} to the original map, replacing
     * any existing entries.  This is used by subclasses that need to slightly
     * alter the data used by the parent locale.
     */
    protected static final JsObject overrideMap(JsObject original, JsObject override) {

        JsPropertyMap<Object> originalAsPropertyMap = Js.asPropertyMap(original);
        JsPropertyMap<Object> overrideAsPropertyMap = Js.asPropertyMap(override);
        String[] keys = JsObject.keys(override);
        for (String key : keys) {
            if (override.hasOwnProperty(key)) {
                originalAsPropertyMap.set(key, overrideAsPropertyMap.get(key));
            }
        }
        return original;
    }

    /**
     * Map of currency codes to CurrencyData.
     */
    protected HashMap<String, CurrencyData> dataMapJava;

    /**
     * JS map of currency codes to CurrencyData objects. Each currency code is
     * assumed to be a valid JS object key.
     */
    protected JsObject dataMapNative;

    /**
     * Map of currency codes to localized currency names. This is kept separate
     * from {@link #dataMapJava} above so that the names can be completely removed by
     * the compiler if they are not used.
     */
    protected HashMap<String, String> namesMapJava;

    /**
     * JS map of currency codes to localized currency names. This is kept separate
     * from {@link #dataMapNative} above so that the names can be completely
     * removed by the compiler if they are not used. Each currency code is assumed
     * to be a valid JS object key.
     */
    protected JsObject namesMapNative;

    /**
     * Return the default currency data for this locale.
     * <p>
     * Generated implementations override this method.
     */
    public CurrencyData getDefault() {
        return getDefaultJava();
    }

    /**
     * Returns an iterator for the list of currencies.
     * <p>
     * Deprecated currencies will not be included.
     */
    @Override
    public final Iterator<CurrencyData> iterator() {
        return iterator(false);
    }

    /**
     * Returns an iterator for the list of currencies, optionally including
     * deprecated ones.
     *
     * @param includeDeprecated true if deprecated currencies should be included
     */
    public final Iterator<CurrencyData> iterator(boolean includeDeprecated) {
        ensureCurrencyMap();
        ArrayList<CurrencyData> collection = new ArrayList<CurrencyData>();

        for (CurrencyData item : dataMapJava.values()) {
            collection.add(item);
        }
        if (!includeDeprecated) {
            ArrayList<CurrencyData> newCollection = new ArrayList<CurrencyData>();
            for (CurrencyData value : collection) {
                if (!value.isDeprecated()) {
                    newCollection.add(value);
                }
            }
            collection = newCollection;
        }
        return Collections.unmodifiableList(collection).iterator();
    }

    /**
     * Lookup a currency based on the ISO4217 currency code.
     *
     * @param currencyCode ISO4217 currency code
     * @return currency data, or null if code not found
     */
    public final CurrencyData lookup(String currencyCode) {
        ensureCurrencyMap();
        return dataMapJava.get(currencyCode);
    }

    /**
     * Lookup a currency name based on the ISO4217 currency code.
     *
     * @param currencyCode ISO4217 currency code
     * @return name of the currency, or null if code not found
     */
    public final String lookupName(String currencyCode) {
        ensureNamesMap();
        String result = namesMapJava.get(currencyCode);
        return (result == null) ? currencyCode : result;
    }

    /**
     * Return the default currency data for this locale.
     * <p>
     * Generated implementations override this method.
     */
    protected CurrencyData getDefaultJava() {
        return new CurrencyDataImpl("USD", "$", 2, "US$", "$");
    }

    /**
     * Return the default currency data for this locale.
     * <p>
     * Generated implementations override this method.
     *
     * @deprecated use {@link #getDefaultJava()}
     */
    @Deprecated
    protected CurrencyData getDefaultNative() {
        return getDefaultJava();
  };

    /**
     * Loads the currency map.
     * <p>
     * Generated implementations override this method.
     */
    protected HashMap<String, CurrencyData> loadCurrencyMapJava() {
        HashMap<String, CurrencyData> result = new HashMap<String, CurrencyData>();
        result.put("USD", new CurrencyDataImpl("USD", "$", 2, "US$", "$"));
        result.put("EUR", new CurrencyDataImpl("EUR", "€", 2, "€", "€"));
        result.put("GBP", new CurrencyDataImpl("GBP", "UK£", 2, "UK£", "£"));
        result.put("JPY", new CurrencyDataImpl("JPY", "¥", 0, "JP¥", "¥"));
        return result;
    }

    /**
     * Loads the currency map from a JS object literal.
     * <p>
     * Generated implementations override this method.
     */
    protected JsObject loadCurrencyMapNative() {
        JsArray<JsObject> usd = new JsArray<>();
        usd.push(JsObject.create("USD"), JsObject.create("$"), JsObject.create(2));

        JsArray<JsObject> eur = new JsArray<>();
        eur.push(JsObject.create("EUR"), JsObject.create("€"), JsObject.create(2));

        JsArray<JsObject> gbp = new JsArray<>();
        gbp.push(JsObject.create("GBP"), JsObject.create("UK£"), JsObject.create(2));

        JsArray<JsObject> jpy = new JsArray<>();
        jpy.push(JsObject.create("JPY"), JsObject.create("¥"), JsObject.create(0));

        JsObject jsObject=JsObject.create(null);
        JsPropertyMap<Object> map = Js.asAny(jsObject).asPropertyMap();
        map.set("USD", usd);
        map.set("EUR", eur);
        map.set("GBP", gbp);
        map.set("JPY", jpy);

        return jsObject;
  };

    /**
     * Loads the currency names map.
     * <p>
     * Generated implementations override this method.
     */
    protected HashMap<String, String> loadNamesMapJava() {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("USD", "US Dollar");
        result.put("EUR", "Euro");
        result.put("GBP", "British Pound Sterling");
        result.put("JPY", "Japanese Yen");
        return result;
    }

    /**
     * Loads the currency names map from a JS object literal.
     * <p>
     * Generated implementations override this method.
     */
    protected JsObject loadNamesMapNative() {
        JsObject jsObject=JsObject.create(null);
        JsPropertyMap<Object> nativeNamesMap = Js.asAny(jsObject).asPropertyMap();
        nativeNamesMap.set("USD", "US Dollar");
        nativeNamesMap.set("EUR", "Euro");
        nativeNamesMap.set("GBP", "British Pound Sterling");
        nativeNamesMap.set("JPY", "Japanese Yen");

        return jsObject;
    }

    ;

    /**
     * Ensure that the map of currency data has been initialized.
     */
    private void ensureCurrencyMap() {
        if (dataMapJava == null) {
            dataMapJava = loadCurrencyMapJava();
        }
    }

    /**
     * Ensure that the map of currency data has been initialized.
     */
    private void ensureNamesMap() {
        if (namesMapJava == null) {
            namesMapJava = loadNamesMapJava();
        }
    }
}

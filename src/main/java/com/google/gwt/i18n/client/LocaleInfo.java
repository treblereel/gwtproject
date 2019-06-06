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
package com.google.gwt.i18n.client;

/**
 * Provides access to the currently-active locale and the list of available
 * locales.
 */
@SuppressWarnings("deprecation")
public class LocaleInfo {

    public static final LocaleInfo getCurrentLocale() {
        /*
         * In the future, we could make additional static methods which returned a
         * LocaleInfo instance for a specific locale (from the set of those the app
         * was compiled with), accessed via a method like:
         *    public static LocaleInfo getLocale(String localeName)
         */
        return new LocaleInfo();
    }

    public boolean hasAnyRTL() {
        return false;
    }

    /**
     * Returns true if this locale is right-to-left instead of left-to-right.
     */
    public final boolean isRTL() {
        return false;
    }
}

/*
 * Copyright 2009 Google Inc.
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
package org.gwtproject.i18n.rg.rebind;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;
import org.gwtproject.i18n.ext.ConfigurationProperty;
import org.gwtproject.i18n.ext.GeneratorContext;
import org.gwtproject.i18n.ext.PropertyOracle;
import org.gwtproject.i18n.ext.SelectionProperty;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.server.GwtLocaleFactoryImpl;
import org.gwtproject.i18n.shared.GwtLocale;
import org.gwtproject.i18n.shared.GwtLocaleFactory;

/** Utility methods for dealing with locales. */
public class LocaleUtils {
  private static final GwtLocaleFactoryImpl factory = new GwtLocaleFactoryImpl();

  /** The token representing the locale property controlling Localization. */
  public static final String PROP_DEFAULT_LOCALE = "default_locale";

  /**
   * The config property identifying the URL query parameter name to possibly get the value of the
   * locale property.
   */
  // @VisibleForTesting
  public static final String PROP_LOCALE_QUERY_PARAM = "locale.queryparam";

  /**
   * The config property identifying the cookie name to possibly get the value of the locale
   * property.
   */
  // @VisibleForTesting
  public static final String PROP_LOCALE_COOKIE = "locale.cookie";

  /** The token representing the locales configuration property. */
  // @VisibleForTesting
  public static final String PROP_LOCALES = "locales";

  /** Multiple generators need to access the shared cache state of LocaleInfoContext. */
  private static final WeakHashMap<GeneratorContext, LocaleInfoContext> localeInfoCtxHolder =
      new WeakHashMap<>();

  /**
   * Create a new LocaleUtils instance for the given PropertyOracle. Returned instances will be
   * immutable and can be shared across threads.
   *
   * @param logger
   * @param propertyOracle
   * @return LocaleUtils instance
   */
  public static LocaleUtils getInstance(
      TreeLogger logger, PropertyOracle propertyOracle, GeneratorContext context) {
    try {
      SelectionProperty localeProp =
          propertyOracle.getSelectionProperty(logger, PROP_DEFAULT_LOCALE);

      ConfigurationProperty runtimeLocaleProp =
          propertyOracle.getConfigurationProperty(logger, PROP_LOCALES);
      ConfigurationProperty queryParamProp =
          propertyOracle.getConfigurationProperty(logger, PROP_LOCALE_QUERY_PARAM);
      ConfigurationProperty cookieProp =
          propertyOracle.getConfigurationProperty(logger, PROP_LOCALE_COOKIE);
      LocaleInfoContext localeInfoCtx = getLocaleInfoCtx(context);
      LocaleUtils localeUtils =
          localeInfoCtx.getLocaleUtils(localeProp, runtimeLocaleProp, queryParamProp, cookieProp);
      if (localeUtils == null) {
        localeUtils = createInstance(localeProp, runtimeLocaleProp, queryParamProp, cookieProp);
        localeInfoCtx.putLocaleUtils(
            localeProp, runtimeLocaleProp, queryParamProp, cookieProp, localeUtils);
      }
      return localeUtils;
    } catch (UnableToCompleteException e) {
      // if we don't have locale properties defined, just return a basic one
      logger.log(TreeLogger.WARN, "Unable to get locale properties, using defaults", e);
      GwtLocale defaultLocale = factory.fromString("default");
      Set<GwtLocale> allLocales = new HashSet<>();

      allLocales.add(defaultLocale);
      return new LocaleUtils(
          defaultLocale, allLocales, allLocales, Collections.<GwtLocale>emptySet(), null, null);
    }
  }

  /**
   * Get a shared GwtLocale factory so instances are cached between all uses.
   *
   * @return singleton GwtLocaleFactory instance.
   */
  public static GwtLocaleFactory getLocaleFactory() {
    return factory;
  }

  private static LocaleUtils createInstance(
      SelectionProperty localeProp,
      ConfigurationProperty prop,
      ConfigurationProperty queryParamProp,
      ConfigurationProperty cookieProp) {
    GwtLocale compileLocale = null;
    Set<GwtLocale> allLocales = new HashSet<>();
    Set<GwtLocale> allCompileLocales = new HashSet<>();
    Set<GwtLocale> runtimeLocales = new HashSet<>();
    String localeName = localeProp.getCurrentValue();
    String queryParam = queryParamProp.getValues().iterator().next();
    if (queryParam.length() == 0) {
      queryParam = null;
    }
    String cookie = cookieProp.getValues().iterator().next();
    if (cookie.length() == 0) {
      cookie = null;
    }
    SortedSet<String> localeValues = new TreeSet(prop.getValues());

    GwtLocaleFactory factoryInstance = getLocaleFactory();
    GwtLocale newCompileLocale = factoryInstance.fromString(localeName);
    compileLocale = newCompileLocale;
    for (String localeValue : localeValues) {
      allCompileLocales.add(factoryInstance.fromString(localeValue));
    }
    allLocales.addAll(allCompileLocales);
    Set<String> rtLocaleNames = prop.getValues();
    if (rtLocaleNames != null) {
      for (String rtLocaleName : rtLocaleNames) {
        GwtLocale rtLocale = factoryInstance.fromString(rtLocaleName);
        if (rtLocale.isDefault()) {
          continue;
        }
        for (GwtLocale search : rtLocale.getCompleteSearchList()) {
          if (search.equals(compileLocale)) {
            runtimeLocales.add(rtLocale);
            allLocales.add(rtLocale);
            break;
          } else if (allCompileLocales.contains(search)) {
            allLocales.add(rtLocale);
            break;
          }
        }
      }
    }
    return new LocaleUtils(
        compileLocale, allLocales, allCompileLocales, runtimeLocales, queryParam, cookie);
  }

  private static synchronized LocaleInfoContext getLocaleInfoCtx(GeneratorContext context) {
    LocaleInfoContext localeInfoCtx = localeInfoCtxHolder.get(context);
    if (localeInfoCtx == null) {
      localeInfoCtx = new LocaleInfoContext();
      localeInfoCtxHolder.put(context, localeInfoCtx);
    }
    return localeInfoCtx;
  }

  private final Set<GwtLocale> allCompileLocales;

  private final Set<GwtLocale> allLocales;

  private final GwtLocale compileLocale;

  private final Set<GwtLocale> runtimeLocales;

  private final String queryParam;

  private final String cookie;

  private LocaleUtils(
      GwtLocale compileLocale,
      Set<GwtLocale> allLocales,
      Set<GwtLocale> allCompileLocales,
      Set<GwtLocale> runtimeLocales,
      String queryParam,
      String cookie) {
    this.compileLocale = compileLocale;
    this.allLocales = Collections.unmodifiableSet(allLocales);
    this.allCompileLocales = Collections.unmodifiableSet(allCompileLocales);
    this.runtimeLocales = Collections.unmodifiableSet(runtimeLocales);
    this.queryParam = queryParam;
    this.cookie = cookie;
  }

  /**
   * Returns the set of all compile-time locales.
   *
   * @return unmodifiable set of all compile-time locales
   */
  public Set<GwtLocale> getAllCompileLocales() {
    return allCompileLocales;
  }

  /**
   * Returns the set of all available locales, whether compile-time locales or runtime locales.
   *
   * @return unmodifiable set of all locales
   */
  public Set<GwtLocale> getAllLocales() {
    return allLocales;
  }

  /** Returns the static compile-time locale for this permutation. */
  public GwtLocale getCompileLocale() {
    return compileLocale;
  }

  /**
   * Return the name of the cookie to potentially get the locale value from.
   *
   * @return the cookie name or null if none
   */
  public String getCookie() {
    return cookie;
  }

  public GwtLocale getDefaultLocale() {
    return factory.fromString("default");
  }

  /**
   * Return the name of the URL query param to potentially get the locale value from.
   *
   * @return the URL query param or null if none
   */
  public String getQueryParam() {
    return queryParam;
  }

  /**
   * Returns a list of locales which are children of the current compile-time locale.
   *
   * @return unmodifiable list of matching locales
   */
  public Set<GwtLocale> getRuntimeLocales() {
    return runtimeLocales;
  }
}

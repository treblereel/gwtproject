/*
 *
 * Copyright © ${year} ${name}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.resources.rg.resource;

import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import org.gwtproject.resources.client.GWT3Resources;
import org.gwtproject.resources.context.AptContext;
import org.gwtproject.resources.ext.BadPropertyValueException;
import org.gwtproject.resources.ext.ConfigurationProperty;
import org.gwtproject.resources.ext.DefaultConfigurationProperty;
import org.gwtproject.resources.ext.UnableToCompleteException;

/** @author Dmitrii Tikhomirov Created by treblereel 11/7/18 */
public final class ConfigurationProperties {

  public static final String KEY_CLIENT_BUNDLE_CACHE_LOCATION = "ClientBundle.cacheLocation";
  public static final String KEY_CLIENT_BUNDLE_CACHE_URL = "ClientBundle.cacheUrl";
  public static final String KEY_CLIENT_BUNDLE_ENABLE_INLINING = "ClientBundle.enableInlining";
  public static final String KEY_CLIENT_BUNDLE_ENABLE_RENAMING = "ClientBundle.enableRenaming";
  public static final String KEY_CSS_RESOURCE_ALLOWED_FUNCTIONS = "CssResource.allowedFunctions";
  public static final String KEY_CSS_RESOURCE_ALLOWED_AT_RULES = "CssResource.allowedAtRules";
  public static final String KEY_GSS_DEFAULT_IN_UIBINDER = "CssResource.gssDefaultInUiBinder";
  public static final String KEY_CSS_RESOURCE_MERGE_ENABLED = "CssResource.mergeEnabled";
  public static final String KEY_CSS_RESOURCE_ENABLE_GSS = "CssResource.enableGss";
  public static final String KEY_CSS_RESOURCE_RESERVED_CLASS_PREFIXES =
      "CssResource.reservedClassPrefixes";
  public static final String KEY_CSS_RESOURCE_OBFUSCATION_PREFIX = "CssResource.obfuscationPrefix";
  public static final String KEY_CSS_RESOURCE_STYLE = "CssResource.style";
  public static final String KEY_CSS_RESOURCE_CONVERSION_MODE = "CssResource.conversionMode";
  private static final String CLIENT_BUNDLE_DEFAULT_CACHE_LOCATION = "src/main/webapp/gwt-cache";
  private static final String CLIENT_BUNDLE_DEFAULT_CACHE_URL = "/gwt-cache/";
  private final Map<String, ConfigurationProperty> holder = new HashMap<>();
  private final AptContext context;
  private final Filer filer;

  public ConfigurationProperties(AptContext context) {
    this.context = context;
    this.filer = context.filer;
    setDefaultProperties();
    setAnnotationProperties();
  }

  private void setAnnotationProperties() {
    Set<Element> elements =
        context.roundEnvironment.getElementsAnnotatedWith(GWT3Resources.class).stream()
            .collect(Collectors.toSet());

    if (elements.size() > 1) {
      String classes =
          elements.stream().map(elm -> elm.toString()).collect(Collectors.joining(", "));
      throw new Error(
          new UnableToCompleteException(
              "There is must be only one class annotated with @GWT3Resources,"
                  + " check ["
                  + classes
                  + "]"));
    }

    if (elements.size() == 1) {
      GWT3Resources gwt3ResourcesConfiguration =
          elements.iterator().next().getAnnotation(GWT3Resources.class);
      set(
          KEY_CLIENT_BUNDLE_ENABLE_INLINING,
          Arrays.asList(gwt3ResourcesConfiguration.clientBundle().enableInlining() + ""),
          true);
      set(
          KEY_CLIENT_BUNDLE_ENABLE_RENAMING,
          Arrays.asList(gwt3ResourcesConfiguration.clientBundle().enableRenaming() + ""),
          true);
      set(
          KEY_CSS_RESOURCE_ALLOWED_FUNCTIONS,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().allowedFunctions()),
          false);
      set(
          KEY_CSS_RESOURCE_ALLOWED_AT_RULES,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().allowedAtRules()),
          false);
      set(
          KEY_GSS_DEFAULT_IN_UIBINDER,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().gssDefaultInUiBinder() + ""),
          true);
      set(
          KEY_CSS_RESOURCE_MERGE_ENABLED,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().mergeEnabled() + ""),
          true);
      set(
          KEY_CSS_RESOURCE_ENABLE_GSS,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().enableGss() + ""),
          true);
      set(
          KEY_CSS_RESOURCE_CONVERSION_MODE,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().conversionMode()),
          true);
      set(
          KEY_CSS_RESOURCE_STYLE,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().style()),
          true);
      set(
          KEY_CSS_RESOURCE_OBFUSCATION_PREFIX,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().obfuscationPrefix()),
          true);
      set(
          KEY_CSS_RESOURCE_RESERVED_CLASS_PREFIXES,
          Arrays.asList(gwt3ResourcesConfiguration.cssResource().reservedClassPrefixes()),
          false);

      if (!gwt3ResourcesConfiguration.clientBundle().cacheLocation().equals("")) {
        set(
            KEY_CLIENT_BUNDLE_CACHE_LOCATION,
            Arrays.asList(gwt3ResourcesConfiguration.clientBundle().cacheLocation()),
            false);
      }

      if (!gwt3ResourcesConfiguration.clientBundle().cacheUrl().equals("")) {
        set(
            KEY_CLIENT_BUNDLE_CACHE_URL,
            Arrays.asList(gwt3ResourcesConfiguration.clientBundle().cacheUrl()),
            false);
      }
    }
  }

  private void setDefaultProperties() {
    set(KEY_CLIENT_BUNDLE_ENABLE_INLINING, Arrays.asList("true"), true);
    set(KEY_CLIENT_BUNDLE_ENABLE_RENAMING, Arrays.asList("true"), true);
    set(KEY_CSS_RESOURCE_ALLOWED_FUNCTIONS, new ArrayList<>(), false);
    set(KEY_CSS_RESOURCE_ALLOWED_AT_RULES, Arrays.asList("-moz-document", "supports"), false);
    set(KEY_GSS_DEFAULT_IN_UIBINDER, Arrays.asList("false"), true);
    set(KEY_CSS_RESOURCE_MERGE_ENABLED, Arrays.asList("true"), true);
    set(KEY_CSS_RESOURCE_ENABLE_GSS, Arrays.asList("true"), true);
    set(KEY_CSS_RESOURCE_CONVERSION_MODE, Arrays.asList("off"), true);
    set(KEY_CSS_RESOURCE_STYLE, Arrays.asList("obf"), true);
    set(KEY_CSS_RESOURCE_OBFUSCATION_PREFIX, Arrays.asList("default"), true);
    set(KEY_CSS_RESOURCE_RESERVED_CLASS_PREFIXES, Arrays.asList("gwt-"), false);
  }

  private void set(String propertyName, List<String> defaulValues, boolean override) {
    Set<String> list = new HashSet<>(defaulValues);
    if (holder.containsKey(propertyName)) {
      if (override) {
        holder.put(propertyName, new DefaultConfigurationProperty(propertyName, list));
      } else {
        holder.get(propertyName).getValues().addAll(defaulValues);
      }
    } else {
      holder.put(propertyName, new DefaultConfigurationProperty(propertyName, list));
    }
  }

  public ConfigurationProperty getConfigurationProperty(String key)
      throws BadPropertyValueException {

    System.out.println("getConfigurationProperty " + key);

    if (holder.containsKey(key)) {
      System.out.println("          " + holder.get(key));
      return holder.get(key);
    }
    throw new BadPropertyValueException(key);
  }
}

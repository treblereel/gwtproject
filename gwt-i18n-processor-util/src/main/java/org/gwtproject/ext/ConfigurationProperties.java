/*
 * Copyright 2019 Google Inc.
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
package org.gwtproject.ext;

import java.util.*;

/** @author Dmitrii Tikhomirov Created by treblereel 11/7/18 */
public final class ConfigurationProperties {

  private final Map<String, ConfigurationProperty> holder = new HashMap<>();

  public ConfigurationProperties() {
    setDefaultProperties();
  }

  private void setDefaultProperties() {
    lookupAndSet("locale", Arrays.asList("default"), false);
  }

  private void lookupAndSet(String propertyName, List<String> defaulValues, boolean override) {
    List<String> list = new ArrayList<>(defaulValues);
    holder.put(
        propertyName, lookup(new DefaultConfigurationProperty(propertyName, list), override));
  }

  private ConfigurationProperty lookup(ConfigurationProperty holder, boolean override) {
    String values = System.getProperty(holder.getName());
    if (values != null) {
      if (override) {
        holder.getValues().clear();
      }
      Arrays.stream(values.split("\\s+")).forEach(e -> holder.getValues().add(e));
    }
    return holder;
  }

  public ConfigurationProperty getConfigurationProperty(String key)
      throws BadPropertyValueException {
    if (holder.containsKey(key)) {
      return holder.get(key);
    }
    throw new BadPropertyValueException(key);
  }
}

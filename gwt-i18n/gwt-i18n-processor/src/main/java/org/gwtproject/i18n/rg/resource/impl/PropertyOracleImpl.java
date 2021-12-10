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

package org.gwtproject.i18n.rg.resource.impl;

import org.gwtproject.i18n.context.AptContext;
import org.gwtproject.i18n.ext.BadPropertyValueException;
import org.gwtproject.i18n.ext.ConfigurationProperty;
import org.gwtproject.i18n.ext.PropertyOracle;
import org.gwtproject.i18n.ext.SelectionProperty;
import org.gwtproject.i18n.ext.TreeLogger;
import org.gwtproject.i18n.ext.UnableToCompleteException;
import org.gwtproject.i18n.rg.resource.ConfigurationProperties;
import org.gwtproject.i18n.rg.resource.StandardSelectionProperty;

/** @author Dmitrii Tikhomirov Created by treblereel 12/5/18 */
public class PropertyOracleImpl implements PropertyOracle {

  public final ConfigurationProperties configurationProperties;

  public PropertyOracleImpl(AptContext aptContext) {
    configurationProperties = new ConfigurationProperties(aptContext.filer);
  }

  @Override
  public ConfigurationProperty getConfigurationProperty(TreeLogger logger, String propertyName)
      throws UnableToCompleteException {
    ConfigurationProperty configurationProperty;
    try {
      configurationProperty = configurationProperties.getConfigurationProperty(propertyName);
    } catch (BadPropertyValueException e) {
      logger.log(TreeLogger.Type.ERROR, "Unable to get configuration property : " + propertyName);
      throw new UnableToCompleteException();
    }
    return configurationProperty;
  }

  @Override
  public SelectionProperty getSelectionProperty(TreeLogger logger, String propertyName)
      throws UnableToCompleteException {
    String value = System.getProperty(propertyName);
    if (value != null) {
      try {
        return new StandardSelectionProperty(
            propertyName,
            value,
            configurationProperties.getConfigurationProperty(propertyName).asSingleValue());
      } catch (BadPropertyValueException e) {
        throw new UnableToCompleteException(e);
      }
    } else {
      try {
        return new StandardSelectionProperty(
            propertyName,
            configurationProperties.getConfigurationProperty(propertyName).asSingleValue(),
            configurationProperties.getConfigurationProperty(propertyName).asSingleValue());
      } catch (BadPropertyValueException e) {

      }
    }
    logger.log(TreeLogger.Type.ERROR, "Unable to get selection property : " + propertyName);
    throw new UnableToCompleteException();
  }
}

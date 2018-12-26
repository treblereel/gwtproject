package org.gwtproject.ext;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 12/5/18
 */
public class PropertyOracleImpl implements PropertyOracle {
    public final ConfigurationProperties configurationProperties;

    public PropertyOracleImpl() {
        configurationProperties = new ConfigurationProperties();
    }

    @Override
    public ConfigurationProperty getConfigurationProperty(String propertyName) throws UnableToCompleteException {
        ConfigurationProperty configurationProperty;
        try {
            configurationProperty = configurationProperties.getConfigurationProperty(propertyName);
        } catch (BadPropertyValueException e) {
            throw new UnableToCompleteException();
        }
        return configurationProperty;
    }

    @Override
    public SelectionProperty getSelectionProperty(Messager logger, String propertyName) throws UnableToCompleteException {
        String value = System.getProperty(propertyName);
        if (value != null) {
            return new StandardSelectionProperty(propertyName, value);
        } else {
            try {
                ConfigurationProperty configurationProperty = configurationProperties.getConfigurationProperty(propertyName);
                return new StandardSelectionProperty(propertyName, configurationProperty.asSingleValue());
            } catch (BadPropertyValueException e) {
                logger.printMessage(Diagnostic.Kind.ERROR, "Unable to get selection property : " + propertyName);
                throw new UnableToCompleteException();
            }
        }
    }
}

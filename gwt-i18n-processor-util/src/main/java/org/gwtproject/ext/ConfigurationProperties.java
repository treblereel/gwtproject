package org.gwtproject.ext;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 11/7/18
 */
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
        holder.put(propertyName, lookup(
                new DefaultConfigurationProperty(propertyName, list), override));
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

    public ConfigurationProperty getConfigurationProperty(String key) throws BadPropertyValueException {
        if (holder.containsKey(key)) {
            return holder.get(key);
        }
        throw new BadPropertyValueException(key);
    }
}

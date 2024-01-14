package org.stupidstick.configuration;

import java.util.Objects;
import java.util.Properties;

public class ConfigurationUtils {
    public static String requireNonNullProperty(String key, Properties properties) {
        return Objects.requireNonNull(properties.getProperty(key), "Property " + key + " is null");
    }
}

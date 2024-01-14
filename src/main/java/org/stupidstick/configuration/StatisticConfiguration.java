package org.stupidstick.configuration;

import org.stupidstick.exceptions.ConfigurationInitializationException;

import java.math.RoundingMode;
import java.util.Properties;

public class StatisticConfiguration {
    private static final String PROPERTIES_PATH = "configuration/statistics.properties";
    private static final String AVG_ROUND_PROPERTY_NAME = "roundScale";
    public static final int AVG_ROUND_SCALE;
    public static final RoundingMode AVG_ROUNDING_MODE = RoundingMode.CEILING;

    static {
        try (var inputStream = StatisticConfiguration.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            AVG_ROUND_SCALE = Integer.parseInt(
                    ConfigurationUtils.requireNonNullProperty(AVG_ROUND_PROPERTY_NAME, properties));

        } catch (Exception exception) {
            throw new ConfigurationInitializationException("Failed to initialize statistics configuration", exception);
        }
    }

}

package org.stupidstick.configuration;

import lombok.Getter;
import org.stupidstick.DataType;
import org.stupidstick.exceptions.ConfigurationInitializationException;

import java.io.FileReader;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

public class OutputConfiguration {
    private static final String PROPERTIES_PATH = "src/main/resources/configuration/paths.properties";
    private static final String FILENAMES_GROUP = "output_filename.";

    private static final EnumMap<DataType, String> propertyNames = new EnumMap<>(Map.of(
            DataType.INTEGER, "integer",
            DataType.DECIMAL, "decimal",
            DataType.STRING, "string"
    ));

    @Getter
    private static final EnumMap<DataType, String> outputFilenames = new EnumMap<>(DataType.class);


    static {
        try (var reader = new FileReader(PROPERTIES_PATH)) {
            Properties properties = new Properties();
            properties.load(reader);

            propertyNames.forEach((type, propertyName) -> outputFilenames.put(type,
                    ConfigurationUtils.requireNonNullProperty(FILENAMES_GROUP + propertyName, properties)));

        } catch (Exception exception) {
            throw new ConfigurationInitializationException("Failed to initialize output filenames configuration", exception);
        }
    }

}

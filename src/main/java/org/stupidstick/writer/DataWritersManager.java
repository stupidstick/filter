package org.stupidstick.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.data.DataType;
import org.stupidstick.configuration.OutputConfiguration;
import org.stupidstick.filter.WritingMode;
import org.stupidstick.filter.FilterUtils;

import java.io.IOException;
import java.util.EnumMap;

@RequiredArgsConstructor
@Slf4j
public class DataWritersManager {
    private final EnumMap<DataType, DataWriter> writers = new EnumMap<>(DataType.class);

    public DataWritersManager(String path, String prefix, WritingMode mode) {
        OutputConfiguration.getOutputFilenames()
                .forEach((type, fileName) -> writers.put(type,
                        new DataWriter(
                                FilterUtils.buildPath(path, prefix + fileName), mode)));
    }

    public void writeData(DataType type, String data) {
        DataWriter writer = writers.get(type);
        if (writer == null) return;
        try {
            writer.writeLine(data);
        } catch (IOException exception) {
            writers.put(type, null);
            log.error("Error writing to file {}. Writing to this file is suspended. " + exception.getMessage(), writer.getFilePath());
        }

    }

    public void close() {
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException exception) {
                log.error("Failed to close file {}. " + exception.getMessage(), writer.getFilePath());
            }
        });
    }

}

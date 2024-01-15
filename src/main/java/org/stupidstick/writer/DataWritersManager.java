package org.stupidstick.writer;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.DataType;
import org.stupidstick.configuration.OutputConfiguration;
import org.stupidstick.filter.FileWritingMode;
import org.stupidstick.filter.FilterUtils;

import java.io.IOException;
import java.util.EnumMap;

@RequiredArgsConstructor
@Slf4j
public class DataWritersManager {
    private final EnumMap<DataType, DataWriter> writers = new EnumMap<>(DataType.class);

    public DataWritersManager(String path, String prefix, FileWritingMode mode) {
        OutputConfiguration.getOutputFilenames()
                .forEach((type, fileName) -> writers.put(type,
                        new DataWriter(
                                FilterUtils.buildPath(path, prefix + fileName), mode)));
    }


    public void close() {
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException exception) {
                log.error("");
            }
        });
    }

}

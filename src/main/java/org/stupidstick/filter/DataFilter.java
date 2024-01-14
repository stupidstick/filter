package org.stupidstick.filter;

import lombok.Getter;
import lombok.NonNull;
import org.stupidstick.DataConverter;
import org.stupidstick.DataType;
import org.stupidstick.configuration.OutputConfiguration;
import org.stupidstick.Pair;
import org.stupidstick.statistic.StatisticMode;
import org.stupidstick.statistic.collectors.DataStatisticCollector;
import org.stupidstick.writer.DataWriter;
import org.stupidstick.writer.FileDataWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;

public class DataFilter {
    private final EnumMap<DataType, DataWriter> writers = new EnumMap<>(DataType.class);
    @Getter
    private final DataStatisticCollector statisticCollector;


    private DataFilter(String outputPrefix, String writingPath, FileWritingMode writingMode, StatisticMode statisticMode) {
        FilterUtils.createDirectories(writingPath);
        OutputConfiguration.getOutputFilenames()
                .forEach((type, fileName) -> writers.put(type,
                        new FileDataWriter(FilterUtils.buildPath(writingPath, outputPrefix + fileName), writingMode)));
        statisticCollector = DataStatisticCollector.valueOf(statisticMode);
    }


    public void filter(String @NonNull ... readingFilePaths) {
        for (String path : readingFilePaths) {
            filterFile(path);
        }
    }

    private void filterFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String data;
            while ((data = reader.readLine()) != null) {
                Pair<DataType, Object> convertedData = DataConverter.convert(data);
                writers.get(convertedData.key()).writeLine(data);
                statisticCollector.add(convertedData);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public static class DataFilterBuilder {
        private String outputPrefix = "";
        private String writingPath = "";
        private FileWritingMode writingMode = FileWritingMode.REWRITE;
        private StatisticMode statisticMode;


        public DataFilterBuilder(@NonNull StatisticMode mode) {
            this.statisticMode = mode;
        }


        public DataFilterBuilder outputPrefix(@NonNull String prefix) {
            this.outputPrefix = prefix;
            return this;
        }

        public DataFilterBuilder writingPath(@NonNull String path) {
            this.writingPath = path;
            return this;
        }

        public DataFilterBuilder writingMode(@NonNull FileWritingMode mode) {
            this.writingMode = mode;
            return this;
        }

        public DataFilterBuilder statisticMode(@NonNull StatisticMode mode) {
            this.statisticMode = mode;
            return this;
        }

        public DataFilter build() {
            return new DataFilter(outputPrefix, writingPath, writingMode, statisticMode);
        }

    }
}

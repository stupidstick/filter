package org.stupidstick.filter;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.DataConverter;
import org.stupidstick.DataType;
import org.stupidstick.Pair;
import org.stupidstick.configuration.OutputConfiguration;
import org.stupidstick.statistic.StatisticMode;
import org.stupidstick.statistic.collectors.DataStatisticCollector;
import org.stupidstick.writer.DataWriter;
import org.stupidstick.writer.DataWritersManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class DataFilter {
    @Getter
    private final DataStatisticCollector statisticCollector;
    @Getter @Setter
    private String outputPrefix;
    @Getter @Setter
    private String writingPath;
    @Getter @Setter
    private FileWritingMode writingMode;
    private DataWritersManager writersManager;


    private DataFilter(String outputPrefix, String writingPath, FileWritingMode writingMode, StatisticMode statisticMode) {
        this.outputPrefix = outputPrefix;
        this.writingPath = writingPath;
        this.writingMode = writingMode;
        FilterUtils.createDirectories(writingPath);
        statisticCollector = DataStatisticCollector.valueOf(statisticMode);
    }


    public void filter(String @NonNull ... readingFilePaths) {
        writersManager = new DataWritersManager(writingPath, outputPrefix, writingMode);
        for (String path : readingFilePaths) {
            filterFile(path);
        }
        writersManager.close();
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
            log.error("Access to file {} is broken. The file will be skipped. " + exception.getMessage(), path);
        }
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

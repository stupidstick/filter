package org.stupidstick.cli;

import com.beust.jcommander.JCommander;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.filter.DataFilter;
import org.stupidstick.filter.WritingMode;
import org.stupidstick.statistics.StatisticMode;
import org.stupidstick.statistics.collectors.DataStatisticCollector;

@Slf4j
public class FilterRunnerByArgs {
    private final FilterArgs args = new FilterArgs();
    public FilterRunnerByArgs(String[] args) {
        parseArgs(args);
    }

    public void run() {
        DataFilter filter = new DataFilter.DataFilterBuilder(parseStatisticMode())
                .outputPrefix(args.getPrefix())
                .writingPath(args.getWritingPaths())
                .writingMode(parseWritingMode())
                .build();
        log.info("Starting filter");
        filter.filter(parseInputPaths());
        log.info("Filtration completed");
        log.info(formatStatistics(filter.getStatisticCollector()));
    }

    private String formatStatistics(DataStatisticCollector dataCollector) {
        StringBuilder builder = new StringBuilder("Statistics:\n");
        dataCollector.getCollectors()
                .forEach((type, collector) ->
                        builder.append(type.getName()).append("s:\n").append(collector.statistic()));
        return builder.toString();
    }

    private String[] parseInputPaths() {
        if (args.getInputPaths() == null)
            throw new NullPointerException("No input files specified");
        return args.getInputPaths().toArray(String[]::new);
    }

    private StatisticMode parseStatisticMode() {
        if (!args.isShortStatisticMode() && !args.isFullStatisticMode())
            throw new IllegalArgumentException("Statistics mode not specified");
        if (args.isShortStatisticMode() && args.isFullStatisticMode())
            throw new IllegalArgumentException("Only one statistics mode must be specified");
        return args.isShortStatisticMode() ? StatisticMode.SHORT : StatisticMode.FULL;
    }

    private WritingMode parseWritingMode() {
        return args.isAppendWritingMode() ? WritingMode.APPEND : WritingMode.REWRITE;
    }

    private void parseArgs(String[] args) {
        JCommander.newBuilder()
                .addObject(this.args)
                .build()
                .parse(args);
    }
}

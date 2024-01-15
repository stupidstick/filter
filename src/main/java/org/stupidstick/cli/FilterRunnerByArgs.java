package org.stupidstick.cli;

import com.beust.jcommander.JCommander;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.filter.DataFilter;
import org.stupidstick.filter.FileWritingMode;
import org.stupidstick.statistic.StatisticMode;
import org.stupidstick.statistic.collectors.DataStatisticCollector;

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
        printStatistic(filter.getStatisticCollector());
    }

    private void printStatistic(DataStatisticCollector dataCollector) {
        dataCollector.getCollectors()
                .forEach((type, collector) ->
                        System.out.println(type.getName() + "s statistics: " + collector.statistic()));
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

    private FileWritingMode parseWritingMode() {
        return args.isAppendWritingMode() ? FileWritingMode.APPEND : FileWritingMode.REWRITE;
    }

    private void parseArgs(String[] args) {
        JCommander.newBuilder()
                .addObject(this.args)
                .build()
                .parse(args);
    }
}

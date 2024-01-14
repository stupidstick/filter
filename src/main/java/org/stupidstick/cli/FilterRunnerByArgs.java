package org.stupidstick.cli;

import com.beust.jcommander.JCommander;
import org.stupidstick.filter.DataFilter;
import org.stupidstick.filter.FileWritingMode;
import org.stupidstick.statistic.StatisticMode;

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
        filter.filter(parseInputPaths());
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

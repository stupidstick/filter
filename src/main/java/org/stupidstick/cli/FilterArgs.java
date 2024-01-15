package org.stupidstick.cli;

import com.beust.jcommander.Parameter;
import lombok.Data;
import lombok.Getter;
import java.util.List;

@Getter
@Data
public class FilterArgs {
    @Parameter(names = "-s")
    private boolean shortStatisticMode;

    @Parameter(names = "-f")
    private boolean fullStatisticMode;

    @Parameter(names = "-p")
    private String prefix = "";

    @Parameter(names = "-o")
    private String writingPaths = "";

    @Parameter(names = "-a")
    private boolean appendWritingMode;

    @Parameter
    private List<String> inputPaths;
}

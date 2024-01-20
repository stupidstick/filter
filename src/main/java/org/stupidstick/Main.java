package org.stupidstick;


import lombok.extern.slf4j.Slf4j;
import org.stupidstick.cli.FilterRunnerByArgs;


@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            FilterRunnerByArgs runner = new FilterRunnerByArgs(args);
            runner.run();
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

}
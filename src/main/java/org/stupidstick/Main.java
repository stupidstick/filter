package org.stupidstick;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.stupidstick.cli.FilterRunnerByArgs;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            FilterRunnerByArgs runner = new FilterRunnerByArgs(args);
            runner.run();
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

    }

}
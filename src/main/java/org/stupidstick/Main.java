package org.stupidstick;


import org.stupidstick.cli.FilterRunnerByArgs;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        args = new String[] {"-f", "input/input.txt"};
        try {
            FilterRunnerByArgs runner = new FilterRunnerByArgs(args);
            runner.run();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

}
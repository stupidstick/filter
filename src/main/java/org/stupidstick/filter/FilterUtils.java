package org.stupidstick.filter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilterUtils {
    public static String buildPath(String path, String fileName) {
        return FileSystems.getDefault().getPath(path, fileName).toString();
    }

    public static void createDirectories(String path) {
        try {
            Files.createDirectories(Paths.get(path));
        } catch (IOException exception) {
            throw new IllegalArgumentException("Failed to create directory: " + path);
        }

    }
}

package org.stupidstick.filter;

import java.nio.file.FileSystems;

public class FilterUtils {
    public static String buildPath(String path, String fileName) {
        return FileSystems.getDefault().getPath(path, fileName).toString();
    }
}

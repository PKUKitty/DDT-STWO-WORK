package com.ddt.utils;

import java.io.IOException;
import java.nio.file.Paths;

public class FileUtils {
    /**
     * Return the absolute path to a file/directory/special
     * <p>
     * Resolves all symbolic links.
     * NB: The file/directory should exist!
     * On error returns null
     */
    public static String getAbsPath(final String path) {
        String realPath = null;
        try {
            realPath = String.valueOf(Paths.get(path).toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realPath;
    }

    /**
     * Return the absolute path to a file/directory/special
     * <p>
     * Resolves all symbolic links.
     * NB: The file/directory should exist!
     * On error returns empty string
     */
    public static String getAbsPathS(final String path) {
        String absPath = getAbsPath(path);
        if (null == absPath) {
            return "";
        }
        return absPath;
    }

    /**
     * split path, only support linux and windows
     *
     * @param path path
     * @return path arrays
     */
    public static String[] splitPath(final String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }

        if (System.getProperty("os.name").startsWith("Linux")) {
            return path.split("/");
        } else if (System.getProperty("os.name").startsWith("Win")) {
            String regex = "\\\\";
            return path.split(regex);
        }
        return null;
    }
}

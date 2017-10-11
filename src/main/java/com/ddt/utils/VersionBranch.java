package com.ddt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO fix it to singleton
 */
public class VersionBranch {

    public static final String versionFileName = "VERSION"; // 3.5.2-65-g8ff183b-dirty

    public static final String branchFileName = "BRANCH"; // fix_selector

    /**
     * base path; C:\Users\YuJun\IdeaProjects\Utils
     */
    private static final String basePath = System.getProperty("user.dir");

    private static boolean isWin = System.getProperty("os.name").toLowerCase().startsWith("win");

    private static String getVersionFilePath() {
        if (!isWin) {
            return basePath + "/" + versionFileName; // linux and osx
        } else {
            return basePath + "\\" + versionFileName;
        }
    }

    private static String getBranchFilePath() {
        if (!isWin) {
            return basePath + "/" + branchFileName; // linux and osx
        } else {
            return basePath + "\\" + branchFileName;
        }
    }

    public static int getCodeVersion() {
        return Integer.valueOf(readFile(getVersionFilePath()).substring(0, 1));
    }

    public static int getSubVersion() {
        return Integer.valueOf(readFile(getVersionFilePath()).substring(2, 2 + 1));
    }

    public static int getPatch() {
        return Integer.valueOf(readFile(getVersionFilePath()).substring(4, 4 + 1));
    }

    public static String getBranch() {
        return readFile(getBranchFilePath());
    }

    private static String readFile(String fileName) {
        String content = "";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            content = reader.readLine(); // one line;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return content;
    }
}

package com.ddt.utils;

public class StringUtils {


    public static boolean isDigit(final char[] arrays) {
        for (char array : arrays) {
            if (!Character.isDigit(array)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(final String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

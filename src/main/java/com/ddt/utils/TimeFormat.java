package com.ddt.utils;

/**
 * format of Time string
 */
public enum TimeFormat {
    HHMMSS(0, "HHmmss"),
    HHMM(1, "HHmm"),
    HHMMSS_COLON(2, "HH:mm:ss"),
    HHMM_COLON(3, "HH:mm");

    private final int seq;

    private final String name; // for java date simpleDateFormat fun

    TimeFormat(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }
}

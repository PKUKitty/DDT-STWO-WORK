package com.ddt.utils;

/**
 * format of Time string
 */
public enum TimeFormat {
    HHMMSS(0),
    HHMM(1),
    HHMMSS_COLON(2), //HH:MM:SS
    HHMM_COLON(3); //HH:MM

    private final int seq;

    TimeFormat(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }
}

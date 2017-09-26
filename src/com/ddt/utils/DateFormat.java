package com.ddt.utils;

/**
 * format of date string
 */
public enum DateFormat {
    YYYYMMDD(0),
    YYMMDD(1),
    MMDDYY(2),
    MMDDYYYY(3),
    DDMMYY(4),
    DDMMYYYY(5),
    DDMONYY(6),
    DDMONYYYY(7);

    private final int seq;

    DateFormat(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }
}

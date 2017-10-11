package com.ddt.utils;

/**
 * format of date string
 */
public enum DateFormat {
    YYYYMMDD(0, "yyyyMMdd"),
    YYMMDD(1, "yyMMdd"),
    MMDDYY(2, "MMddyy"),
    MMDDYYYY(3, "MMddyyyy"),
    DDMMYY(4, "ddMMyy"),
    DDMMYYYY(5, "ddMMyyyy"),
    DDMONYY(6, "ddWWyy"), // MON is week number in month? TODO
    DDMONYYYY(7, "ddWWyyyy");

    private final int seq;
    private final String name; // for java date simple format

    DateFormat(int seq, String name) {
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

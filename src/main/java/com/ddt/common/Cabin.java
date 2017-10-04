package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 1;
 * char stand for cabin code;
 */
public class Cabin extends SizedString {

    public static final int CABIN_LEN = 1;

    public Cabin(final String value) {
        super(value,CABIN_LEN);
    }

}

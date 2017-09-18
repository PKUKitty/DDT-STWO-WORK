package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 8;
 */
public class Fbc extends SizedString {

    public static final int FBC_LEN = 8;

    public Fbc(final String value) {
        super(value, FBC_LEN);
    }

}

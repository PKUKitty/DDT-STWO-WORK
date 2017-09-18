package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 2;
 */
public class Crs extends SizedString {

    public static final int CRS_LEN = 2;

    public Crs(final String value) {
        super(value, CRS_LEN);
    }

}

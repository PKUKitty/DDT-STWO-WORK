package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 5;
 */
public class FbcType extends SizedString {

    public static final int FBC_TYPE_LEN = 3;

    public FbcType(final String value) {
        super(value, FBC_TYPE_LEN);
    }

}

package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 15;
 */
public class FbcOverride extends SizedString {

    public static final int FBC_OVERRIDE_LEN = 15;

    public FbcOverride(final String value) {
        super(value, FBC_OVERRIDE_LEN);
    }

}

package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 10;
 */
public class TktCode extends SizedString {

    public static final int TKT_CODE_LEN = 10;

    public TktCode(final String value) {
        super(value, TKT_CODE_LEN);
    }

}

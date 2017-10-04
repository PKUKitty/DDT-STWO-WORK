package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 1;
 * char stand for area code;
 */
public class AreaCode extends SizedString {

    public static final int AREA_CODE_LEN = 1;

    public AreaCode(final String value) {
        super(value,AREA_CODE_LEN);
    }

}



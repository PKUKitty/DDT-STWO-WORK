package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 5;
 */
public class CityCode extends SizedString {

    public static final int CITY_CODE_LEN = 3;

    public CityCode(final String value) {
        super(value, CITY_CODE_LEN);
    }

}



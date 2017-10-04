package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 3;
 */
public class BrandNameCode extends SizedString {

    public static final int BRAND_NAME_CODE_LEN = 3;

    public BrandNameCode(final String value) {
        super(value, BRAND_NAME_CODE_LEN);
    }

}

package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 3;
 */
public class CurrencyCode extends SizedString {

    public static final int CURRENCY_CODE_LEN = 3;

    public CurrencyCode(final String value) {
        super(value, CURRENCY_CODE_LEN);
    }

}

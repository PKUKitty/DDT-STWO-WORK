package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 3;
 */
public class CarrierCode extends SizedString {

    public static final int CARRIER_CODE_LEN = 3;

    public CarrierCode(final String value) {
        super(value, CARRIER_CODE_LEN);
    }

}



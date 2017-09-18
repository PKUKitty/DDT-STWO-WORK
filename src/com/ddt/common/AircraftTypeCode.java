package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 3;
 */
public class AircraftTypeCode extends SizedString{

    public static final int AIRCRAFT_TYPE_CODE_LEN = 3;

    public AircraftTypeCode(final String value) {
        super(value,AIRCRAFT_TYPE_CODE_LEN);
    }

}



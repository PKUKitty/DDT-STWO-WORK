package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 48;
 */
public class UUID extends SizedString {

    public static final int UUID_LEN = 48;

    public UUID(final String value) {
        super(value, UUID_LEN);
    }

}

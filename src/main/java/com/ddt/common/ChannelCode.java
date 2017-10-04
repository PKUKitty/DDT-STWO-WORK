package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 2;
 */
public class ChannelCode extends SizedString {

    public static final int CHANNEL_CODE_LEN = 2;

    public ChannelCode(final String value) {
        super(value,CHANNEL_CODE_LEN);
    }

}

package com.ddt.common;

import com.ddt.utils.SizedString;

/**
 * SIZE = 10;
 */
public class ChannelIdCode extends SizedString {

    public static final int CHANNEL_ID_CODE_LEN = 10;

    public ChannelIdCode(final String value) {
        super(value,CHANNEL_ID_CODE_LEN);
    }

}

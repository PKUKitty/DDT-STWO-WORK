/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.privateclassdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Immutable stew class, protected with private class data pattern
 */
public class ImmutableStew {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableStew.class);

    private StewData data;

    public ImmutableStew(int numPotatoes, int numCarrots, int numMeat, int numPeppers) {
        data = new StewData(numPotatoes, numCarrots, numMeat, numPeppers);
    }

    /**
     * Mix the stew
     */
    public void mix() {
        LOGGER.info("Mixing the immutable stew we find: {} potatoes, {} carrots, {} meat and {} peppers",
                data.getNumPotatoes(), data.getNumCarrots(), data.getNumMeat(), data.getNumPeppers());
    }

}

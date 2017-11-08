/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeacefulState implements State {

    public static final Logger LOGGER = LoggerFactory.getLogger(PeacefulState.class);

    private Mammoth mammoth;

    public PeacefulState(Mammoth mammoth) {
        this.mammoth = mammoth;
    }

    @Override
    public void onEnterState() {
        LOGGER.info("{} calms down.", mammoth);
    }

    @Override
    public void observe() {
        LOGGER.info("{} is calm and peaceful.", mammoth);
    }
}

/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.templatemethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubtleMethod extends StealingMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubtleMethod.class);

    @Override
    protected String pickTarget() {
        return "shop keeper";
    }

    @Override
    protected void confuseTarget(String target) {
        LOGGER.info("Approach the {} with tears running and hug him!", target);
    }

    @Override
    protected void stealTheItem(String target) {
        LOGGER.info("While in close contact grab the {}'s wallet.", target);
    }
}

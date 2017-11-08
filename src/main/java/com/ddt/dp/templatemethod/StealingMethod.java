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

public abstract class StealingMethod {

    public static final Logger LOGGER = LoggerFactory.getLogger(StealingMethod.class);

    protected abstract String pickTarget();

    protected abstract void confuseTarget(String target);

    protected abstract void stealTheItem(String target);

    public void steal() {
        String target = pickTarget();
        LOGGER.info("The target has been chosen as {}.", target);
        confuseTarget(target);
        stealTheItem(target);
    }
}

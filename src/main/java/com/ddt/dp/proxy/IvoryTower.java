/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IvoryTower implements WizardTower {

    private static final Logger LOGGER = LoggerFactory.getLogger(IvoryTower.class);

    @Override
    public void enter(Wizard wizard) {
        LOGGER.info("{} enter the tower.", wizard);
    }
}

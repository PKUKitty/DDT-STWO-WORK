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

public class WizardTowerProxy implements WizardTower {

    private static final Logger LOGGER = LoggerFactory.getLogger(WizardTowerProxy.class);

    private static final int NUM_WIZARD_ALLOWED = 3;

    private int numWizard; //default is 0;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizard < NUM_WIZARD_ALLOWED) {
            tower.enter(wizard);
            numWizard++;
        } else {
            LOGGER.info("{} is not allowed to enter.", wizard);
        }
    }
}

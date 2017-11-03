/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldierVisitor implements UnitVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoldierVisitor.class);

    @Override
    public void visitSoldier(Soldier soldier) {
        LOGGER.info("Greetings {}", soldier);
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        //Do nothing
    }

    @Override
    public void visitCommander(Commander commander) {
        //Do nothing
    }
}

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

public class CommanderVisitor implements UnitVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommanderVisitor.class);

    @Override
    public void visitSoldier(Soldier soldier) {
        //Do nothing
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        //Do nothing
    }

    @Override
    public void visitCommander(Commander commander) {
        LOGGER.info("Good to see you {}", commander);
    }
}

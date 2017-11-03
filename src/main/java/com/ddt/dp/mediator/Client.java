/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    public static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        AbstractMediator mediator = new Mediator(collA, collB);

        LOGGER.info("set A, affect B");
        collA.setNumber(1288, mediator);
        LOGGER.info("collA number: {}", collA.getNumber());
        LOGGER.info("collB number: {}", collB.getNumber());

        LOGGER.info("set B, affect A");
        collB.setNumber(87653, mediator);
        LOGGER.info("collB number: {}", collB.getNumber());
        LOGGER.info("collA number: {}", collA.getNumber());
    }
}

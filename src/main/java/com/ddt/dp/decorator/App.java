/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    public static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        //simple troll
        LOGGER.info("A simple looking troll approaches");
        Troll troll = new SimpleTroll();
        troll.attack();
        troll.fleeBattle();
        LOGGER.info("Simple troll power {}.\n", troll.getAttackPower());

        // change the behavior of the simple troll by adding a decorator
        LOGGER.info("A troll with huge club surprises you.");
        Troll clubbed = new ClubbedTroll(troll);
        clubbed.attack();
        clubbed.fleeBattle();
        LOGGER.info("Clubbed troll power {}.\n", clubbed.getAttackPower());
    }
}

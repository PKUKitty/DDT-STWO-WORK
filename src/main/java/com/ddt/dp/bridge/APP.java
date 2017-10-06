/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APP {
    private static final Logger LOGGER = LoggerFactory.getLogger(APP.class);

    public static void main(String[] args) {
        LOGGER.info("The knight receives an enchanted sword");
        Sword enchantedSword = new Sword(new SoulEatingEnchantment());
        enchantedSword.wield();
        enchantedSword.swing();
        enchantedSword.unwield();

        LOGGER.info("The knight receives an enchanted sword");
        Sword flyingSword = new Sword(new FlyingEnchantment());
        flyingSword.wield();
        flyingSword.swing();
        flyingSword.unwield();

        LOGGER.info("The valkyrie receives an enchanted hammer");
        Hammer hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.swing();
        hammer.unwield();
    }
}

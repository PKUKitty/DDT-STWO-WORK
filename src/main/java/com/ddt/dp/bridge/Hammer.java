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

public class Hammer implements Weapon {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hammer.class);

    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        LOGGER.info("The hammer is wielded");
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The hammer is swinged");
        enchantment.apply();
    }

    @Override
    public void unwield() {
        LOGGER.info("The hammer is unwielded");
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}

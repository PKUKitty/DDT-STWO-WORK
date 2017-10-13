/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APP {
    private static final Logger LOGGER = LoggerFactory.getLogger(APP.class);

    public static void main(String[] args) {
        TreasureChest chest = new TreasureChest();

        ItemIterator ringIterator = chest.iterator(ItemType.RING);
        while (ringIterator.hasNext()) {
            LOGGER.info(ringIterator.next().toString());
        }

        LOGGER.info("----------");

        ItemIterator potionIterator = chest.iterator(ItemType.POTION);
        while (potionIterator.hasNext()) {
            LOGGER.info(potionIterator.next().toString());
        }

        LOGGER.info("----------");

        ItemIterator weaponIterator = chest.iterator(ItemType.WEAPON);
        while (weaponIterator.hasNext()) {
            LOGGER.info(weaponIterator.next().toString());
        }

        LOGGER.info("----------");

        ItemIterator it = chest.iterator(ItemType.ANY);
        while (it.hasNext()) {
            LOGGER.info(it.next().toString());
        }
    }
}

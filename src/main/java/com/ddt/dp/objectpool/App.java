/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        OliphauntPool pool = new OliphauntPool();
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt1 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt1);
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt2 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt2);
        Oliphaunt oliphaunt3 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt3);
        LOGGER.info(pool.toString());
        LOGGER.info("Checking in {}", oliphaunt1);
        pool.checkIn(oliphaunt1);
        LOGGER.info("Checking in {}", oliphaunt2);
        pool.checkIn(oliphaunt2);
        LOGGER.info(pool.toString());
        Oliphaunt oliphaunt4 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt4);
        Oliphaunt oliphaunt5 = pool.checkOut();
        LOGGER.info("Checked out {}", oliphaunt5);
        LOGGER.info(pool.toString());
    }
}

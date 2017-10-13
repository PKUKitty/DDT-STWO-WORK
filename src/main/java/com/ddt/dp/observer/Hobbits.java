/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hobbits implements WeatherObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hobbits.class);

    @Override
    public void update(WeatherType currentWeather) {
        switch (currentWeather) {
            case COLD:
                LOGGER.info("The hobbits are shivering in the cold weather.");
                break;
            case RAINY:
                LOGGER.info("The hobbits look for cover from the rain.");
                break;
            case SUNNY:
                LOGGER.info("The happy hobbits bade in the warm sun.");
                break;
            case WINDY:
                LOGGER.info("The hobbits hold their hats tightly in the windy weather.");
                break;
            default:
                break;
        }
    }
}

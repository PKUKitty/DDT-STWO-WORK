/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    private final String name;

    private final FruitShop fruitShop;

    private final FruitBowl fruitBowl;

    /**
     * Customer constructor
     */
    public Customer(String name, FruitShop fruitShop) {
        this.name = name;
        this.fruitShop = fruitShop;
        this.fruitBowl = new FruitBowl();
    }

    /**
     * The Customer repeatedly takes Fruit from the FruitShop until no Fruit
     * remains.
     */
    public void run() {

        while (fruitShop.countFruit() > 0) {
            FruitBowl bowl = fruitShop.takeBowl();
            Fruit fruit;

            if (bowl != null && (fruit = bowl.take()) != null) {
                LOGGER.info("{} took an {}", name, fruit);
                fruitBowl.put(fruit);
                fruitShop.returnBowl(bowl);
            }
        }

        LOGGER.info("{} took {}", name, fruitBowl);

    }

}

/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

public class FruitShop {

    private FruitBowl[] bowls = {
            new FruitBowl(),
            new FruitBowl(),
            new FruitBowl()
    };

    private boolean[] available = {
            true,
            true,
            true
    };

    private Semaphore semaphore;

    public FruitShop() {
        for (int i = 0; i < 100; i++) {
            bowls[0].put(new Fruit(Fruit.FruitType.APPLE));
            bowls[1].put(new Fruit(Fruit.FruitType.ORANGE));
            bowls[2].put(new Fruit(Fruit.FruitType.LEMON));
        }

        semaphore = new Semaphore(3);
    }

    /**
     * @return The amount of Fruit left in the shop.
     */
    public synchronized int countFruit() {
        return bowls[0].countFruit() + bowls[1].countFruit() + bowls[2].countFruit();
    }

    /**
     * Method called by Customer to get a FruitBowl from the shop. This method
     * will try to acquire the Semaphore before returning the first available
     * FruitBowl.
     */
    public synchronized FruitBowl takeBowl() {

        FruitBowl bowl = null;

        try {
            semaphore.acquire();

            if (available[0]) {
                bowl = bowls[0];
                available[0] = false;
            } else if (available[1]) {
                bowl = bowls[1];
                available[1] = false;
            } else if (available[2]) {
                bowl = bowls[2];
                available[2] = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return bowl;
    }


    /**
     * Method called by a Customer instance to return a FruitBowl to the shop.
     * This method releases the Semaphore, making the FruitBowl available to
     * another Customer.
     */
    public synchronized void returnBowl(FruitBowl bowl) {
        if (bowl == bowls[0]) {
            available[0] = true;
        } else if (bowl == bowls[1]) {
            available[1] = true;
        } else if (bowl == bowls[2]) {
            available[2] = true;
        }
    }


}

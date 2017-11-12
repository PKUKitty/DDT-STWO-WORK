/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

import java.util.ArrayList;
import java.util.List;

public class FruitBowl {

    private List<Fruit> fruit = new ArrayList<>();

    public int countFruit() {
        return fruit.size();
    }

    public void put(Fruit f) {
        fruit.add(f);
    }

    public Fruit take() {
        if (fruit.isEmpty()) {
            return null;
        } else {
            return fruit.remove(0);
        }
    }

    @Override
    public String toString() {
        int apples = 0;
        int oranges = 0;
        int lemons = 0;

        for (Fruit f : fruit) {
            switch (f.getType()) {
                case APPLE:
                    apples++;
                    break;
                case ORANGE:
                    oranges++;
                    break;
                case LEMON:
                    lemons++;
                    break;
                default:
            }
        }

        return apples + " Apples, " + oranges + " Oranges, and " + lemons + " Lemons";
    }
}

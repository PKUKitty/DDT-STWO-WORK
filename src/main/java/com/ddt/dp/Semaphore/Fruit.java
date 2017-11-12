/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

public class Fruit {

    public static enum FruitType {
        ORANGE, APPLE, LEMON
    }

    private FruitType type;

    public Fruit(FruitType type) {
        this.type = type;
    }

    public FruitType getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case APPLE:
                return "Apple";
            case LEMON:
                return "Lemon";
            case ORANGE:
                return "Orange";
            default:
                return "";
        }
    }
}

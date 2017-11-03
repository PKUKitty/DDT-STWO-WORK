/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.privateclassdata;

/**
 * Stew ingredients
 */
public class StewData {

    private int numPotatoes;

    private int numCarrots;

    private int numMeat;

    private int numPeppers;

    public StewData(int numPotatoes, int numCarrots, int numMeat, int numPeppers) {
        this.numPotatoes = numPotatoes;
        this.numCarrots = numCarrots;
        this.numMeat = numMeat;
        this.numPeppers = numPeppers;
    }

    public int getNumPotatoes() {
        return numPotatoes;
    }

    public int getNumCarrots() {
        return numCarrots;
    }

    public int getNumMeat() {
        return numMeat;
    }

    public int getNumPeppers() {
        return numPeppers;
    }
}

/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.threadpool;

public class CoffeeMakingTask extends Task {

    private static final int TIME_PER_CUP = 100;

    public CoffeeMakingTask(int numCups) {
        super(numCups * TIME_PER_CUP);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
    }
}

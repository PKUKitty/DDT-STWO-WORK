/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

public class Semaphore implements Lock {

    private final int licenses;

    /**
     * The number of concurrent resource accesses which are allowed.
     */
    private int counter;

    public Semaphore(int licenses) {
        this.licenses = licenses;
        this.counter = licenses;
    }

    /**
     * Returns the number of licenses managed by the Semaphore
     */
    public int getNumLicenses() {
        return licenses;
    }

    /**
     * Returns the number of available licenses
     */
    public int getAvailableLicenses() {
        return counter;
    }

    @Override
    public synchronized void acquire() throws InterruptedException {
        while (counter == 0) {
            wait();
        }
        counter--;
    }

    @Override
    public synchronized void release() {
        if (counter < licenses) {
            counter++;
            notify();
        }
    }
}

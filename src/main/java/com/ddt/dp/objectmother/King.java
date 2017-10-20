/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.objectmother;

public class King implements Royalty {

    boolean isDrunk = false;

    boolean isHappy = false;

    @Override
    public void makeDrunk() {
        isDrunk = true;
    }

    @Override
    public void makeSober() {
        isDrunk = false;
    }

    @Override
    public void makeHappy() {
        isHappy = true;
    }

    @Override
    public void makeUnhappy() {
        isHappy = false;
    }

    /**
     * Method to flirt to a queen.
     *
     * @param queen Queen which should be flirted.
     */
    public void flirt(Queen queen) {
        boolean flirtStatus = queen.getFlirted(this);
        if (!flirtStatus) {
            this.makeUnhappy();
        } else {
            this.makeHappy();
        }
    }
}

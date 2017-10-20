/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.objectmother;

public class Queen implements Royalty {

    private boolean isDrunk = false;

    private boolean isHappy = false;

    private boolean isFlirty = false;

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

    public boolean isFlirty() {
        return isFlirty;
    }

    public void setFlirtiness(boolean flirtiness) {
        isFlirty = flirtiness;
    }

    /**
     * Method which is called when the king is flirting to a queen.
     *
     * @param king King who initialized the flirt.
     * @return A value which describes if the flirt was successful or not.
     */
    public boolean getFlirted(King king) {
        return this.isFlirty && king.isHappy && !king.isDrunk;
    }
}

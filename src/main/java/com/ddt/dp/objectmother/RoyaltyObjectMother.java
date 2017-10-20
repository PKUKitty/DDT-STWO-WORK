/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.objectmother;

public final class RoyaltyObjectMother {

    /**
     * Method to create a sober and unhappy king. The standard paramters are set.
     *
     * @return An instance of {@link com.ddt.dp.objectmother.King} with the standard properties.
     */
    public static King createSoberUnhappyKing() {
        return new King();
    }


    /**
     * Method of the object mother to create a drunk king.
     *
     * @return A drunk {@link com.ddt.dp.objectmother.King}.
     */
    public static King createDrunkKing() {
        King king = new King();
        king.makeDrunk();
        return king;
    }

    /**
     * Method to create a happy king.
     *
     * @return A happy {@link com.ddt.dp.objectmother.King}.
     */
    public static King createHappyKing() {
        King king = new King();
        king.makeHappy();
        return king;
    }

    /**
     * Method to create a happy and drunk king.
     *
     * @return A drunk and happy {@link com.ddt.dp.objectmother.King}.
     */
    public static King createHappyDrunkKing() {
        King king = new King();
        king.makeHappy();
        king.makeDrunk();
        return king;
    }

    /**
     * Method to create a flirty queen.
     *
     * @return A flirty {@link com.ddt.dp.objectmother.Queen}.
     */
    public static Queen createFlirtyQueen() {
        Queen queen = new Queen();
        queen.setFlirtiness(true);
        return queen;
    }

    /**
     * Method to create a not flirty queen.
     *
     * @return A not flirty {@link com.ddt.dp.objectmother.Queen}.
     */
    public static Queen createNotFlirtyQueen() {
        return new Queen();
    }
}

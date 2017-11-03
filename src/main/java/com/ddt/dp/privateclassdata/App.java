/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.privateclassdata;

public class App {
    public static void main(String[] args) {
        //stew is mutable
        Stew stew = new Stew(1, 2, 3, 4);
        stew.mix();
        stew.taste();
        stew.mix();

        // immutable stew protected with Private Class Data pattern
        ImmutableStew immutableStew = new ImmutableStew(2, 4, 3, 6);
        immutableStew.mix();
    }
}

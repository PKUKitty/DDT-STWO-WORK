/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.Semaphore;

public class App {

    public static void main(String[] args) {
        FruitShop shop = new FruitShop();
        new Customer("Peter", shop).start();
        new Customer("Paul", shop).start();
        new Customer("Mary", shop).start();
        new Customer("John", shop).start();
        new Customer("Ringo", shop).start();
        new Customer("George", shop).start();
    }
}

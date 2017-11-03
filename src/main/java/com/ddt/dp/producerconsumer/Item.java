/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.producerconsumer;

public class Item {

    private String producer;

    private int id;

    public Item(String producer, int id) {
        this.producer = producer;
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public int getId() {
        return id;
    }
}

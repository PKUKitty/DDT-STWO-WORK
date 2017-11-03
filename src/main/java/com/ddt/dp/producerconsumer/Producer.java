/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.producerconsumer;

import java.util.Random;

public class Producer {

    private final ItemQueue queue;

    private final String name;

    private int itemId;

    public Producer(ItemQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    /**
     * Put item in the queue
     */
    public void produce() throws InterruptedException {
        Item item = new Item(name, itemId++);
        queue.put(item);
        Random random = new Random();
        Thread.sleep(random.nextInt(2000));
    }
}

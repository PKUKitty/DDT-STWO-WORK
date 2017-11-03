/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.producerconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final ItemQueue queue;

    private final String name;

    public Consumer(ItemQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    public void consume() throws InterruptedException {
        Item item = queue.take();
        LOGGER.info("Consumer [{}] consume item [{}] produced by [{}]", name, item.getId(), item.getProducer());
    }
}

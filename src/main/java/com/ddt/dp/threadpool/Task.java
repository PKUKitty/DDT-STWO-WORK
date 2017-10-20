/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Task {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

    private final int id;

    private final int timeMs;

    public Task(int timeMs) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.timeMs = timeMs;
    }

    public int getId() {
        return id;
    }

    public int getTimeMs() {
        return timeMs;
    }

    @Override
    public String toString() {
        return String.format("id=%d timeMs=%d", id, timeMs);
    }
}

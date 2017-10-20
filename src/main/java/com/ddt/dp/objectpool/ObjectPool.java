/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.objectpool;

import java.util.HashSet;
import java.util.Set;

public abstract class ObjectPool<T> {

    private Set<T> available = new HashSet<>();

    private Set<T> inUse = new HashSet<>();

    protected abstract T create();

    public synchronized T checkOut() {
        if (available.isEmpty()) {
            available.add(create());
        }

        T instance = available.iterator().next();
        available.remove(instance);
        inUse.add(instance);
        return instance;
    }

    public synchronized void checkIn(T instance) {
        inUse.remove(instance);
        available.add(instance);
    }

    @Override
    public synchronized String toString() {
        return String.format("Pool available=%d inUse=%d", available.size(), inUse.size());
    }
}

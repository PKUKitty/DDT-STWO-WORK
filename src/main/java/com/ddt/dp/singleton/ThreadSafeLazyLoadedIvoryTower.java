/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.singleton;

public class ThreadSafeLazyLoadedIvoryTower {
    private static ThreadSafeLazyLoadedIvoryTower instance;

    private ThreadSafeLazyLoadedIvoryTower(){
        if(instance != null){
            throw new IllegalStateException("Already initialized");
        }
    }

    public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance(){
        if(instance == null){
            instance = new ThreadSafeLazyLoadedIvoryTower();
        }
        return instance;
    }
}

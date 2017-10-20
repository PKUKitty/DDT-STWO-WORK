/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.caching;

public enum CachingPolicy {

    THROUGH("through"), AROUND("around"), BEHIND("behind"), ASIDE("aside");

    private final String policy;

    CachingPolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }
}

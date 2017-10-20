/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.nullobject;

public class App {
    public static void main(String[] args) {
        Node root =
                new NodeImpl("1", new NodeImpl("11", new NodeImpl("111", NullNode.getInstance(),
                        NullNode.getInstance()), NullNode.getInstance()), new NodeImpl("12",
                        NullNode.getInstance(), new NodeImpl("122", NullNode.getInstance(),
                        NullNode.getInstance())));
        root.walk();
    }
}

/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.visitor;


public abstract class Unit {

    private Unit[] children;

    public Unit(Unit[] children) {
        if(children != null){
            this.children = children.clone();
        }
    }

    public void accept(UnitVisitor visitor) {
        for (Unit child : children) {
            child.accept(visitor);
        }
    }
}

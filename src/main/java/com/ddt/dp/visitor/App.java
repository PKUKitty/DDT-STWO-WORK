/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.visitor;

/**
 *
 * Visitor pattern defines mechanism to apply operations on nodes in hierarchy. New operations can
 * be added without altering the node interface.
 * <p>
 * In this example there is a unit hierarchy beginning from {@link Commander}. This hierarchy is
 * traversed by visitors. {@link SoldierVisitor} applies its operation on {@link Soldier}s,
 * {@link SergeantVisitor} on {@link Sergeant}s and so on.
 *
 */
public class App {

    public static void main(String[] args) {
        Commander commander =
                new Commander(new Sergeant(new Soldier(), new Soldier(), new Soldier()), new Sergeant(
                        new Soldier(), new Soldier(), new Soldier()));

        commander.accept(new SoldierVisitor());
        commander.accept(new SergeantVisitor());
        commander.accept(new CommanderVisitor());
    }
}

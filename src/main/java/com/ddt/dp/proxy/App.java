/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.proxy;

public class App {

    public static void main(String[] args) {
        WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());

        proxy.enter(new Wizard("Red Wizard"));
        proxy.enter(new Wizard("White Wizard"));
        proxy.enter(new Wizard("Black Wizard"));
        proxy.enter(new Wizard("Green Wizard"));
        proxy.enter(new Wizard("Brown Wizard"));
    }
}

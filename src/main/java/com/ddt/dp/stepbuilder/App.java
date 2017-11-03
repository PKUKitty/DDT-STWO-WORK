/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.stepbuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Character warrior = CharacterStepBuilder.newBuilder().name("Amberjill").fighterClass("Paladin").withWeapon
                ("Sword").noAbilities().build();

        LOGGER.info(warrior.toString());

        Character mage =
                CharacterStepBuilder.newBuilder().name("Riobard").wizardClass("Sorcerer")
                        .withSpell("Fireball").withAbility("Fire Aura").withAbility("Teleport")
                        .noMoreAbilities().build();

        LOGGER.info(mage.toString());

        Character thief =
                CharacterStepBuilder.newBuilder().name("Desmond").fighterClass("Rogue").noWeapon().build();

        LOGGER.info(thief.toString());
    }
}

/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.iterator;

import java.util.ArrayList;
import java.util.List;

public class TreasureChest {

    private List<Item> items;

    public TreasureChest() {
        items = new ArrayList<>();
        items.add(new Item(ItemType.POTION, "Potion of courage"));
        items.add(new Item(ItemType.RING, "Ring of shadows"));
        items.add(new Item(ItemType.POTION, "Potion of wisdom"));
        items.add(new Item(ItemType.POTION, "Potion of blood"));
        items.add(new Item(ItemType.WEAPON, "Sword of silver +1"));
        items.add(new Item(ItemType.POTION, "Potion of rust"));
        items.add(new Item(ItemType.POTION, "Potion of healing"));
        items.add(new Item(ItemType.RING, "Ring of armor"));
        items.add(new Item(ItemType.WEAPON, "Steel halberd"));
        items.add(new Item(ItemType.WEAPON, "Dagger of poison"));
    }

    ItemIterator iterator(ItemType itemType) {
        return new TreasureChestItemIterator(this, itemType);
    }

    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        list.addAll(items);
        return list;
    }
}

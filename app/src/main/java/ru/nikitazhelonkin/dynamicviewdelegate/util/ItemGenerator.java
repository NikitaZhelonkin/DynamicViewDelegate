package ru.nikitazhelonkin.dynamicviewdelegate.util;

import ru.nikitazhelonkin.dynamicviewdelegate.Item;

public class ItemGenerator {

    private static int id;

    public static Item next(){
        return new Item(id++, Colors.getRandomColor());
    }
}

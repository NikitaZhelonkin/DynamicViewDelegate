package ru.nikitazhelonkin.dynamicviewdelegate;

public class Item {

    private int id;
    private int color;

    public Item(int id, int color){
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

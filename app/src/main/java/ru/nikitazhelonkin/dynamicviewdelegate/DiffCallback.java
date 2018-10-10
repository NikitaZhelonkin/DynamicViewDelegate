package ru.nikitazhelonkin.dynamicviewdelegate;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffCallback extends DiffUtil.Callback {

    private List<Item> oldItems;
    private List<Item> newItems;

    public DiffCallback(@Nullable List<Item> oldItems, @Nullable List<Item> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems == null ? 0 : oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems == null ? 0 : newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldItems.get(oldItemPosition);
        Item newItem = newItems.get(newItemPosition);
        return oldItem.getColor() == newItem.getColor();
    }
}

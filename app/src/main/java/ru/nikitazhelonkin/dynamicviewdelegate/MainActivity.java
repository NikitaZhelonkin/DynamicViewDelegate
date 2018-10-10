package ru.nikitazhelonkin.dynamicviewdelegate;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import ru.nikitazhelonkin.dynamicviewdelegate.util.Colors;
import ru.nikitazhelonkin.dynamicviewdelegate.util.ItemGenerator;

public class MainActivity extends AppCompatActivity {


    private RadioGroup mRadioGroup;

    private DynamicLinearLayout mDynamicView;

    private Adapter mAdapter;

    private List<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = findViewById(R.id.rgOperations);

        mAdapter = new Adapter();
        mAdapter.setOnItemClickListener(this::onItemClick);

        mDynamicView = findViewById(R.id.dynamicView);
        mDynamicView.setAdapter(mAdapter);

        mItems = loadItems();
        invalidateAdapter(mItems, false);
    }

    public void onItemClick(int position) {
        int checkedRadioButton = mRadioGroup.getCheckedRadioButtonId();
        if (checkedRadioButton == R.id.rbDelete) {
            removeItemAtPosition(position);
        } else if (checkedRadioButton == R.id.rbAdd) {
            addItemAtPosition(position + 1);
        } else if (checkedRadioButton == R.id.rbChange) {
            changeItemColorAtPosition(position);
        } else if (checkedRadioButton == R.id.rbMove) {
            moveItemAtPosition(position);
        }
    }

    public void removeItemAtPosition(int position) {
        mItems.remove(position);
        invalidateAdapter(mItems, true);
    }

    public void addItemAtPosition(int position) {
        mItems.add(position, ItemGenerator.next());
        invalidateAdapter(mItems, true);
    }

    public void changeItemColorAtPosition(int position) {
        Item item = mItems.get(position);
        Item newItem = new Item(item.getId(), Colors.getRandomColor());
        mItems.set(position, newItem);
        invalidateAdapter(mItems, true);
    }

    public void moveItemAtPosition(int position) {
        if (position == 0) return;
        Item item = mItems.get(position);
        mItems.remove(position);
        mItems.add(0, item);
        invalidateAdapter(mItems, true);

    }

    private void invalidateAdapter(List<Item> items, boolean animate) {
        mDynamicView.setLayoutTransition(animate ? new LayoutTransition() : null);
        mAdapter.setData(items, animate);
    }

    private List<Item> loadItems() {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            result.add(ItemGenerator.next());
        }
        return result;
    }


}

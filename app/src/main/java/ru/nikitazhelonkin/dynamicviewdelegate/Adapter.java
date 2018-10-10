package ru.nikitazhelonkin.dynamicviewdelegate;

import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.nikitazhelonkin.lib.DynamicViewDelegate;

public class Adapter extends DynamicViewDelegate.Adapter<Adapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<Item> mItems;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        if (mItems != null) {
            return mItems.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Item> items, boolean hotSwap) {
        List<Item> lastItems = mItems;
        mItems = new ArrayList<>(items);
        if (hotSwap) {
            DiffUtil.calculateDiff(new DiffCallback(lastItems, mItems))
                    .dispatchUpdatesTo(this);
        } else {
            notifyDataChanged();
        }
    }

    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position, Object payload) {
        viewHolder.bind(mItems.get(position));
    }

    class ViewHolder extends DynamicViewDelegate.ViewHolder {

        private TextView tv;

        ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        void bind(Item item) {
            tv.setBackgroundColor(item.getColor());
            tv.setText(String.format(Locale.getDefault(), "id:%d color:%s", item.getId(), Integer.toHexString(item.getColor())));
        }
    }
}

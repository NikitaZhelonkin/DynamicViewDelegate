package ru.nikitazhelonkin.dynamicviewdelegate;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.nikitazhelonkin.lib.DynamicViewDelegate;


public class DynamicLinearLayout extends LinearLayout {

    private DynamicViewDelegate mDynamicViewDelegate = new DynamicViewDelegate(this);

    public DynamicLinearLayout(Context context) {
        super(context);
    }

    public DynamicLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DynamicLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAdapter(DynamicViewDelegate.Adapter adapter) {
        mDynamicViewDelegate.setAdapter(adapter);
    }

    public DynamicViewDelegate.Adapter getAdapter() {
        return mDynamicViewDelegate.getAdapter();
    }
}

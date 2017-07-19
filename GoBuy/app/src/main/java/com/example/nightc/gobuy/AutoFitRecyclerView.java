package com.example.nightc.gobuy;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Oppai on 7/18/2017.
 */

//A custom recycler view that measures the screen size and automatically sets the GridColumns
//does not work because i cant enter the android:columnWidth in the RecyclerView fields
//Will use Custom GridManager
public class AutoFitRecyclerView extends RecyclerView {
    private GridLayoutManager manager;
    private int columnWidth = -1;


    public AutoFitRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AutoFitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            manager.setSpanCount(spanCount);
        }
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            // list the attributes we want to fetch
            // columnWidth is what GridView uses, so we use it too
            int[] attrsArray = {
                    android.R.attr.columnWidth
            };

            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);

            //retrieve the value of the 0 index, which is columnWidth
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        manager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(manager);


    }
}


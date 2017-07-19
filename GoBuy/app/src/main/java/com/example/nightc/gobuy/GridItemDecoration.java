package com.example.nightc.gobuy;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Oppai on 7/17/2017.
 */

//Used to set the spaces between activity cards in the grid
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int Space;

    public GridItemDecoration(int space) {
        Space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = Space;
        outRect.bottom = Space;
        outRect.right = Space;

        if(parent.getChildLayoutPosition(view) == 0)
            outRect.top = Space;
        else
            outRect.top = 0;
    }
}

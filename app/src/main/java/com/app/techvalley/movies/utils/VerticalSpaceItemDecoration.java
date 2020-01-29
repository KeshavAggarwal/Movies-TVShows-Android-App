package com.app.techvalley.movies.utils;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by KeshavAggarwal on 11/02/17.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;

    }
}

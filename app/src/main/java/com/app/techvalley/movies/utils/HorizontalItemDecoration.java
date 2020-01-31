package com.app.techvalley.movies.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Keshav Aggarwal on 01/02/20.
 */
public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {

    private int horizontalSpacing;

    private int start;
    private int middle;
    private int end;

    private int type;

    public HorizontalItemDecoration(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
        type = 0;
    }

    public HorizontalItemDecoration(int start, int middle, int end) {
        this.start = start;
        this.end = end;
        this.middle = middle;

        type = 1;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        if (type == 0) {
            if (position != state.getItemCount() - 1) {
                outRect.right = horizontalSpacing;
            }
        }else if (type == 1){
            //First Item
            if (position == 0){
                outRect.left = start;
                if (state.getItemCount() == 1){
                    //Single Item
                    outRect.right = end;
                }else{
                    outRect.right = middle;
                }
            }else if (position == state.getItemCount() - 1){
                //Last Item
                outRect.right =end;
            }else{
                outRect.right = middle;
            }
        }
    }
}

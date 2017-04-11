package com.example.acer.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class ViewHolderTVShow extends RecyclerView.ViewHolder {
    TextView tvShowType;
    RecyclerView horizontalRecyclerView;
    View divider;
    TextView seeAlltextView;


    public ViewHolderTVShow(View itemView) {
        super(itemView);
        tvShowType = (TextView) itemView.findViewById(R.id.tvShowTypeTextView);
        seeAlltextView = (TextView) itemView.findViewById(R.id.seeAllTextView);
        horizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.activityMainRecyclerViewHorizontal);
        divider = (View) itemView.findViewById(R.id.activityMainDivider);

    }
}

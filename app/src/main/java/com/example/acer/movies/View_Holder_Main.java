package com.example.acer.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 07/01/17.
 */

public class View_Holder_Main extends RecyclerView.ViewHolder {
    TextView movieType;
    RecyclerView horizontalRecyclerView;
    View divider;
    TextView seeAlltextView;


    public View_Holder_Main(View itemView) {
        super(itemView);
        movieType = (TextView) itemView.findViewById(R.id.movieTypeTextView);
        seeAlltextView = (TextView)itemView.findViewById(R.id.seeAllTextView);
        horizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.activityMainRecyclerViewHorizontal);
        divider = (View) itemView.findViewById(R.id.activityMainDivider);

    }
}

package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 30/03/17.
 */
public class ViewHolderSearch extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView thumbnailImage;
    TextView name;
    TextView releaseDate;
    TextView rating;

    public ViewHolderSearch(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        thumbnailImage = (ImageView) itemView.findViewById(R.id.thumbnailImageView);
        name = (TextView) itemView.findViewById(R.id.nameTextView);
        releaseDate = (TextView) itemView.findViewById(R.id.releaseDateTextView);
        rating = (TextView) itemView.findViewById(R.id.ratingTextView);
    }
}

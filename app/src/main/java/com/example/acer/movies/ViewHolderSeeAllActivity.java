package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 31/01/17.
 */

public class ViewHolderSeeAllActivity extends RecyclerView.ViewHolder {

    CardView cv;
    ImageView movieThumbnailImage;
    TextView movieName;
    TextView movieReleaseDate;
    TextView rating;


    public ViewHolderSeeAllActivity(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        movieThumbnailImage = (ImageView) itemView.findViewById(R.id.thumbnailImageView);
        movieName = (TextView) itemView.findViewById(R.id.nameTextView);
        movieReleaseDate = (TextView) itemView.findViewById(R.id.releaseDateTextView);
        rating = (TextView) itemView.findViewById(R.id.ratingTextView);

    }
}

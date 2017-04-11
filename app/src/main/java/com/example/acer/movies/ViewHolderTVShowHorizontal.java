package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class ViewHolderTVShowHorizontal extends RecyclerView.ViewHolder {
    CardView cv;
    ImageView tvShowThumbnailImage;
    TextView tvShowName;
    TextView tvShowReleaseDate;
    TextView rating;

    ViewHolderTVShowHorizontal(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        tvShowThumbnailImage = (ImageView) itemView.findViewById(R.id.tvShowThumbnailImageView);
        tvShowName = (TextView) itemView.findViewById(R.id.tvShowNameTextView);
        tvShowReleaseDate = (TextView) itemView.findViewById(R.id.tvShowReleaseDateTextView);
        rating = (TextView) itemView.findViewById(R.id.ratingTextView);

    }
}
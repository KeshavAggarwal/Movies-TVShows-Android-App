package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class ViewHolderSimilarMovies extends RecyclerView.ViewHolder {

    CardView similarMoviesCardView;
    ImageView similarMoviesThumbnailImageView;
    TextView similarMoviesNameTextView;
    TextView similarMoviesReleaseDateTextView;
    TextView similarmoviesRatingTextView;

    public ViewHolderSimilarMovies(View itemView) {
        super(itemView);
        similarMoviesCardView = (CardView) itemView.findViewById(R.id.similarMoviesCardView);
        similarMoviesThumbnailImageView = (ImageView) itemView.findViewById(R.id.similarMoviesThumbnailImageView);
        similarMoviesNameTextView = (TextView) itemView.findViewById(R.id.similarMoviesNameTextView);
        similarMoviesReleaseDateTextView = (TextView) itemView.findViewById(R.id.similarMoviesReleaseDateTextView);
        similarmoviesRatingTextView = (TextView) itemView.findViewById(R.id.similarmoviesRatingTextView);
    }
}

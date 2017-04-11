package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 10/02/17.
 */

public class ViewHolderMovieCast extends RecyclerView.ViewHolder {
    ImageView movieCastImage;
    TextView movieCastName;
    TextView movieCastCharacter;
    View movieCastDivider;

    ViewHolderMovieCast(View itemView) {
        super(itemView);
        movieCastImage = (ImageView) itemView.findViewById(R.id.movieCastImage);
        movieCastName = (TextView) itemView.findViewById(R.id.movieCastName);
        movieCastCharacter = (TextView) itemView.findViewById(R.id.moviCastCharacter);
        movieCastDivider = (View) itemView.findViewById(R.id.movieCastDivider);

    }
}
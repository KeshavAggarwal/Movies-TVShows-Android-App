package com.example.acer.movies;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class ViewHolderMovieTrailerThumbnails extends RecyclerView.ViewHolder{
    CardView trailerCardView;
    ImageView trailerThumbnail;
    TextView trailerThumbnailName;

    public ViewHolderMovieTrailerThumbnails(View itemView) {
        super(itemView);
        trailerThumbnail = (ImageView) itemView.findViewById(R.id.trailerThumbnail);
        trailerCardView = (CardView) itemView.findViewById(R.id.trailerCardView);
        trailerThumbnailName = (TextView) itemView.findViewById(R.id.trailerThumbnailName);
    }
}

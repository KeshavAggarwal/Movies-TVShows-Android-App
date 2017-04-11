package com.example.acer.movies;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class RecyclerAdapterSimilarMovies extends RecyclerView.Adapter<ViewHolderSimilarMovies> {

    Context mContext;
    ArrayList<Movie> mSimilarMovies;

    public RecyclerAdapterSimilarMovies(ArrayList<Movie> similarMovies, Context context) {
        mSimilarMovies = similarMovies;
        mContext = context;
    }

    @Override
    public ViewHolderSimilarMovies onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movies_item_view, parent, false);
        ViewHolderSimilarMovies holder = new ViewHolderSimilarMovies(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSimilarMovies holder, final int position) {
        if (mSimilarMovies != null) {
            holder.similarMoviesNameTextView.setText(mSimilarMovies.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mSimilarMovies.get(position).posterPath).into(holder.similarMoviesThumbnailImageView);
            if (mSimilarMovies.get(position).getDate().length() >= 5) {
                String date = mSimilarMovies.get(position).date.substring(0, 4);
                holder.similarMoviesReleaseDateTextView.setText(date);
            }
            String rating = Double.toString(mSimilarMovies.get(position).rating);
            holder.similarmoviesRatingTextView.setText(rating);
            holder.similarMoviesCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.similarMoviesThumbnailImageView, holder.similarMoviesThumbnailImageView.getTransitionName()).toBundle();
                    intent.setClass(mContext, AboutMovieActivity.class);
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mSimilarMovies.get(position).getId());
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mSimilarMovies.get(position).getPosterPath());
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mSimilarMovies.get(position).getTitle());
                    mContext.startActivity(intent, bundle);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mSimilarMovies.size();
    }
}

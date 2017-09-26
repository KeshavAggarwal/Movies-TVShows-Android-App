package com.example.acer.movies.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.movies.AboutMovieActivity;
import com.example.acer.movies.utils.IntentConstants;
import com.example.acer.movies.models.Movie;
import com.example.acer.movies.R;
import com.example.acer.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class RecyclerAdapterSimilarMovies extends RecyclerView.Adapter<RecyclerAdapterSimilarMovies.ViewHolder> {

    Context mContext;
    private ArrayList<Movie> mSimilarMovies;

    public RecyclerAdapterSimilarMovies(ArrayList<Movie> similarMovies, Context context) {
        mSimilarMovies = similarMovies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movies_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mSimilarMovies != null) {
            holder.similarMoviesNameTextView.setText(mSimilarMovies.get(position).getTitle());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mSimilarMovies.get(position).getPosterPath()).into(holder.similarMoviesThumbnailImageView);
            if (mSimilarMovies.get(position).getDate().length() >= 5) {
                String date = mSimilarMovies.get(position).getDate().substring(0, 4);
                holder.similarMoviesReleaseDateTextView.setText(date);
            }
            String rating = Double.toString(mSimilarMovies.get(position).getRating());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView similarMoviesCardView;
        ImageView similarMoviesThumbnailImageView;
        TextView similarMoviesNameTextView;
        TextView similarMoviesReleaseDateTextView;
        TextView similarmoviesRatingTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            similarMoviesCardView = (CardView) itemView.findViewById(R.id.similarMoviesCardView);
            similarMoviesThumbnailImageView = (ImageView) itemView.findViewById(R.id.similarMoviesThumbnailImageView);
            similarMoviesNameTextView = (TextView) itemView.findViewById(R.id.similarMoviesNameTextView);
            similarMoviesReleaseDateTextView = (TextView) itemView.findViewById(R.id.similarMoviesReleaseDateTextView);
            similarmoviesRatingTextView = (TextView) itemView.findViewById(R.id.similarmoviesRatingTextView);
        }
    }

}

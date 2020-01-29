
package com.app.techvalley.movies.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.techvalley.movies.AboutMovieActivity;
import com.app.techvalley.movies.utils.IntentConstants;
import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.R;
import com.app.techvalley.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by KeshavAggarwal on 31/01/17.
 */


public class RecyclerViewAdpterSeeAllActivity extends RecyclerView.Adapter<RecyclerViewAdpterSeeAllActivity.ViewHolder> {
    private ArrayList<Movie> mMovies;
    Context mContext;

    public RecyclerViewAdpterSeeAllActivity(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_all_recyclerview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mMovies != null) {
            holder.movieName.setText(mMovies.get(position).getTitle());
            Picasso.get().load(URLConstants.IMAGE_BASE_URL + mMovies.get(position).getPosterPath()).into(holder.movieThumbnailImage);
            if (mMovies.get(position).getDate().length() >= 5) {
                String date = mMovies.get(position).getDate().substring(0, 4);
                holder.movieReleaseDate.setText(date);
            }
            String rating = Double.toString(mMovies.get(position).getRating());
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.movieThumbnailImage, holder.movieThumbnailImage.getTransitionName()).toBundle();
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mMovies.get(position).getId());
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mMovies.get(position).getPosterPath());
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mMovies.get(position).getTitle());
                    intent.setClass(mContext, AboutMovieActivity.class);
                    mContext.startActivity(intent, bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView movieThumbnailImage;
        TextView movieName;
        TextView movieReleaseDate;
        TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            movieThumbnailImage = itemView.findViewById(R.id.thumbnailImageView);
            movieName = itemView.findViewById(R.id.nameTextView);
            movieReleaseDate = itemView.findViewById(R.id.releaseDateTextView);
            rating = itemView.findViewById(R.id.ratingTextView);

        }
    }
}



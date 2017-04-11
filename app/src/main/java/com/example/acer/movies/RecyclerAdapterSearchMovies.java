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
 * Created by KeshavAggarwal on 30/03/17.
 */

public class RecyclerAdapterSearchMovies extends RecyclerView.Adapter<ViewHolderSearch> {
    Context mContext;
    ArrayList<Movie> mSearchedMovies;

    public RecyclerAdapterSearchMovies(ArrayList<Movie> searchedMovies, Context context) {
        mContext = context;
        mSearchedMovies = searchedMovies;
    }

    @Override
    public ViewHolderSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_list_item, parent, false);
        ViewHolderSearch holder = new ViewHolderSearch(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSearch holder, final int position) {
        if (mSearchedMovies != null) {
            holder.name.setText(mSearchedMovies.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mSearchedMovies.get(position).posterPath).into(holder.thumbnailImage);
            if (mSearchedMovies.get(position).getDate().length() >= 5) {
                String date = mSearchedMovies.get(position).date.substring(0, 4);
                holder.releaseDate.setText(date);
            }
            String rating = Double.toString(mSearchedMovies.get(position).rating);
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.thumbnailImage, holder.thumbnailImage.getTransitionName()).toBundle();
                    intent.setClass(mContext, AboutMovieActivity.class);
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mSearchedMovies.get(position).getId());
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mSearchedMovies.get(position).getPosterPath());
                    intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mSearchedMovies.get(position).getTitle());
                    mContext.startActivity(intent, bundle);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mSearchedMovies.size();
    }
}

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
 * Created by KeshavAggarwal on 12/02/17.
 */

public class RecyclerAdapterSeeAllTvshows extends RecyclerView.Adapter<ViewHolderSeeAllActivity> {

    ArrayList<TVShow> mTvShows;
    Context mContext;

    public RecyclerAdapterSeeAllTvshows(ArrayList<TVShow> tvShows, Context context) {
        mTvShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolderSeeAllActivity onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_all_recyclerview_item, parent, false);
        ViewHolderSeeAllActivity holder = new ViewHolderSeeAllActivity(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSeeAllActivity holder, final int position) {
        if (mTvShows != null) {
            holder.movieName.setText(mTvShows.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTvShows.get(position).posterPath).into(holder.movieThumbnailImage);
            if (mTvShows.get(position).getDate().length() >= 5) {
                String date = mTvShows.get(position).date.substring(0, 4);
                holder.movieReleaseDate.setText(date);
            }
            String rating = Double.toString(mTvShows.get(position).rating);
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.movieThumbnailImage, holder.movieThumbnailImage.getTransitionName()).toBundle();
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTvShows.get(position).id);
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTvShows.get(position).posterPath);
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTvShows.get(position).title);
                    intent.setClass(mContext, AboutTVShowActivity.class);
                    mContext.startActivity(intent, bundle);

                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mTvShows.size();
    }
}

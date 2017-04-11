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
 * Created by KeshavAggarwal on 31/03/17.
 */

public class RecyclerAdapterSearchTvShows extends RecyclerView.Adapter<ViewHolderSearch> {
    Context mContext;
    ArrayList<TVShow> mTvShows;

    public RecyclerAdapterSearchTvShows(ArrayList<TVShow> searchedShows, Context context) {
        mContext = context;
        mTvShows = searchedShows;
    }

    @Override
    public ViewHolderSearch onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_tvshow_list_item, parent, false);
        ViewHolderSearch holder = new ViewHolderSearch(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSearch holder, final int position) {
        if (mTvShows != null) {
            holder.name.setText(mTvShows.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTvShows.get(position).posterPath).into(holder.thumbnailImage);
            if (mTvShows.get(position).getDate().length() >= 5) {
                String date = mTvShows.get(position).date.substring(0, 4);
                holder.releaseDate.setText(date);
            }
            String rating = Double.toString(mTvShows.get(position).rating);
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.thumbnailImage, holder.thumbnailImage.getTransitionName()).toBundle();
                    intent.setClass(mContext, AboutTVShowActivity.class);
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTvShows.get(position).getId());
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTvShows.get(position).getPosterPath());
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTvShows.get(position).getTitle());
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

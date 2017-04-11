package com.example.acer.movies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 03/03/17.
 */

public class RecyclerAdapterTVShowTrailer extends RecyclerView.Adapter<ViewHolderTVShowsTrailerThumbnails> {

    Context mContext;
    ArrayList<Trailer> mTrailerTvShowsArraylist;

    public RecyclerAdapterTVShowTrailer(ArrayList<Trailer> trailerTvShows, Context context) {
        mTrailerTvShowsArraylist = trailerTvShows;
        mContext = context;
    }

    @Override
    public ViewHolderTVShowsTrailerThumbnails onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_thumbnail_image_view, parent, false);
        ViewHolderTVShowsTrailerThumbnails holder = new ViewHolderTVShowsTrailerThumbnails(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTVShowsTrailerThumbnails holder, final int position) {
        if (mTrailerTvShowsArraylist != null){
            Picasso.with(mContext).load(URLConstants.TRAILER_THUMBNAIL_IMAGE_URL + mTrailerTvShowsArraylist.get(position).getKey() + "/hqdefault.jpg").into(holder.trailerThumbnail);
            holder.trailerThumbnailName.setText(mTrailerTvShowsArraylist.get(position).getName());
            holder.trailerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, YouTubeAcitivity.class);
                    intent.putExtra("VIDEO_KEY", mTrailerTvShowsArraylist.get(position).getKey());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mTrailerTvShowsArraylist.size();
    }
}

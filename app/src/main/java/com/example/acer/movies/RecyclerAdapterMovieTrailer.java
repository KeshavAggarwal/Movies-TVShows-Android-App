package com.example.acer.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class RecyclerAdapterMovieTrailer extends RecyclerView.Adapter<ViewHolderMovieTrailerThumbnails> {
    Context mContext;
    ArrayList<Trailer> mTrailerMoviesArraylist;

    public RecyclerAdapterMovieTrailer(ArrayList<Trailer> trailerMovies, Context context) {
        mTrailerMoviesArraylist = trailerMovies;
        mContext = context;
    }

    @Override
    public ViewHolderMovieTrailerThumbnails onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_thumbnail_image_view, parent, false);
        ViewHolderMovieTrailerThumbnails holder = new ViewHolderMovieTrailerThumbnails(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMovieTrailerThumbnails holder, final int position) {
        if (mTrailerMoviesArraylist != null) {
            Picasso.with(mContext).load(URLConstants.TRAILER_THUMBNAIL_IMAGE_URL + mTrailerMoviesArraylist.get(position).getKey() + "/hqdefault.jpg").into(holder.trailerThumbnail);
            holder.trailerThumbnailName.setText(mTrailerMoviesArraylist.get(position).getName());

            holder.trailerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, YouTubeAcitivity.class);
                    intent.putExtra("VIDEO_KEY", mTrailerMoviesArraylist.get(position).getKey());
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mTrailerMoviesArraylist.size();
    }
}

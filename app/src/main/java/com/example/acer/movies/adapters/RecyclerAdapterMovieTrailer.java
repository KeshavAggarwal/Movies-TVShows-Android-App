package com.example.acer.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.movies.R;
import com.example.acer.movies.models.Trailer;
import com.example.acer.movies.YouTubeAcitivity;
import com.example.acer.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 19/02/17.
 */

public class RecyclerAdapterMovieTrailer extends RecyclerView.Adapter<RecyclerAdapterMovieTrailer.ViewHolder> {
    Context mContext;
    private ArrayList<Trailer> mTrailerMoviesArraylist;

    public RecyclerAdapterMovieTrailer(ArrayList<Trailer> trailerMovies, Context context) {
        mTrailerMoviesArraylist = trailerMovies;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_thumbnail_image_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView trailerCardView;
        ImageView trailerThumbnail;
        TextView trailerThumbnailName;

        public ViewHolder(View itemView) {
            super(itemView);
            trailerThumbnail = (ImageView) itemView.findViewById(R.id.trailerThumbnail);
            trailerCardView = (CardView) itemView.findViewById(R.id.trailerCardView);
            trailerThumbnailName = (TextView) itemView.findViewById(R.id.trailerThumbnailName);
        }
    }
}

package com.example.acer.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.Network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class RecyclerViewAdapterTVShowHorizontal extends RecyclerView.Adapter<ViewHolderTVShowHorizontal> {

    ArrayList<TVShow> mTVShows;
    Context mContext;
    private OnRecyclerViewitemClicklistener listener;
    private int verticalPosition;

    public RecyclerViewAdapterTVShowHorizontal(ArrayList<TVShow> tvShows, Context context) {
        mTVShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolderTVShowHorizontal onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_cardview_tvshows, parent, false);
        ViewHolderTVShowHorizontal holder = new ViewHolderTVShowHorizontal(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderTVShowHorizontal holder, final int position) {
        if (mTVShows != null){
            holder.tvShowName.setText(mTVShows.get(position).title);
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTVShows.get(position).posterPath).into(holder.tvShowThumbnailImage);
            if (mTVShows.get(position).getDate().length() >= 5) {
                String date = mTVShows.get(position).date.substring(0, 4);
                holder.tvShowReleaseDate.setText(date);
            }
            String rating = Double.toString(mTVShows.get(position).rating).substring(0, 3);
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onRecyclerViewItemClicked(verticalPosition, position, holder.tvShowThumbnailImage);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mTVShows.size();
    }

    public void setOnItemClickListener(OnRecyclerViewitemClicklistener listener, int verticalPosition) {
        this.listener = listener;
        this.verticalPosition = verticalPosition;
    }
}

package com.example.acer.movies.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.movies.OnRecyclerViewitemClicklistener;
import com.example.acer.movies.R;
import com.example.acer.movies.models.TVShow;
import com.example.acer.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class RecyclerViewAdapterTVShowHorizontal extends RecyclerView.Adapter<RecyclerViewAdapterTVShowHorizontal.ViewHolder> {

    private ArrayList<TVShow> mTVShows;
    Context mContext;
    private OnRecyclerViewitemClicklistener listener;
    private int verticalPosition;

    public RecyclerViewAdapterTVShowHorizontal(ArrayList<TVShow> tvShows, Context context) {
        mTVShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_cardview_tvshows, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mTVShows != null){
            holder.tvShowName.setText(mTVShows.get(position).getTitle());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTVShows.get(position).getPosterPath()).into(holder.tvShowThumbnailImage);
            if (mTVShows.get(position).getDate().length() >= 5) {
                String date = mTVShows.get(position).getDate().substring(0, 4);
                holder.tvShowReleaseDate.setText(date);
            }
            String rating = Double.toString(mTVShows.get(position).getRating()).substring(0, 3);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView tvShowThumbnailImage;
        TextView tvShowName;
        TextView tvShowReleaseDate;
        TextView rating;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            tvShowThumbnailImage = (ImageView) itemView.findViewById(R.id.tvShowThumbnailImageView);
            tvShowName = (TextView) itemView.findViewById(R.id.tvShowNameTextView);
            tvShowReleaseDate = (TextView) itemView.findViewById(R.id.tvShowReleaseDateTextView);
            rating = (TextView) itemView.findViewById(R.id.ratingTextView);

        }
    }
}

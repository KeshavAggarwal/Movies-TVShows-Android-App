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
 * Created by KeshavAggarwal on 13/03/17.
 */

public class RecyclerViewAdapterTVShowCast extends RecyclerView.Adapter<ViewHolderTVShowCast> {
    ArrayList<Cast> mTvShowCast;
    Context mContext;

    public RecyclerViewAdapterTVShowCast(ArrayList<Cast> tvShowCastMain, Context context) {
        mTvShowCast = tvShowCastMain;
        mContext = context;
    }

    @Override
    public ViewHolderTVShowCast onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_cast_list_item_view, parent, false);
        ViewHolderTVShowCast holder = new ViewHolderTVShowCast(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTVShowCast holder, int position) {
        if (mTvShowCast != null) {
            holder.tvshowCastName.setText(mTvShowCast.get(position).getName());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTvShowCast.get(position).getProfile_path()).into(holder.tvshowCastImage);
            String character = "as " + mTvShowCast.get(position).character;
            holder.tvshowCastCharacter.setText(character);
        }
    }

    @Override
    public int getItemCount() {
        return mTvShowCast.size();
    }
}

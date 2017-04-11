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
 * Created by KeshavAggarwal on 10/02/17.
 */

public class RecyclerViewAdapterMovieCast extends RecyclerView.Adapter<ViewHolderMovieCast> {
    ArrayList<Cast> mMovieCast;
    Context mContext;

    public RecyclerViewAdapterMovieCast(ArrayList<Cast> movieCastMain, Context context) {
        mMovieCast = movieCastMain;
        mContext = context;
    }

    @Override
    public ViewHolderMovieCast onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cast_list_item_view, parent, false);
        ViewHolderMovieCast holder = new ViewHolderMovieCast(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMovieCast holder, int position) {
        if (mMovieCast != null) {
            holder.movieCastName.setText(mMovieCast.get(position).getName());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mMovieCast.get(position).getProfile_path()).into(holder.movieCastImage);
            String character = "as " + mMovieCast.get(position).character;
            holder.movieCastCharacter.setText(character);
        }

    }

    @Override
    public int getItemCount() {
        return mMovieCast.size();
    }
}

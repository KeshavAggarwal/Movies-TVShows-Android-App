package com.example.acer.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.movies.models.Cast;
import com.example.acer.movies.R;
import com.example.acer.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 10/02/17.
 */

public class RecyclerViewAdapterMovieCast extends RecyclerView.Adapter<RecyclerViewAdapterMovieCast.ViewHolder> {
    private ArrayList<Cast> mMovieCast;
    Context mContext;

    public RecyclerViewAdapterMovieCast(ArrayList<Cast> movieCastMain, Context context) {
        mMovieCast = movieCastMain;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cast_list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mMovieCast != null) {
            holder.movieCastName.setText(mMovieCast.get(position).getName());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mMovieCast.get(position).getProfile_path()).into(holder.movieCastImage);
            String character = "as " + mMovieCast.get(position).getCharacter();
            holder.movieCastCharacter.setText(character);
        }

    }

    @Override
    public int getItemCount() {
        return mMovieCast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movieCastImage;
        TextView movieCastName;
        TextView movieCastCharacter;
        View movieCastDivider;

        ViewHolder(View itemView) {
            super(itemView);
            movieCastImage = (ImageView) itemView.findViewById(R.id.movieCastImage);
            movieCastName = (TextView) itemView.findViewById(R.id.movieCastName);
            movieCastCharacter = (TextView) itemView.findViewById(R.id.moviCastCharacter);
            movieCastDivider = (View) itemView.findViewById(R.id.movieCastDivider);

        }
    }
}

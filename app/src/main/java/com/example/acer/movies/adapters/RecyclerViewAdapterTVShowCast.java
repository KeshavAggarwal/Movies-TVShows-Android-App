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
 * Created by KeshavAggarwal on 13/03/17.
 */

public class RecyclerViewAdapterTVShowCast extends RecyclerView.Adapter<RecyclerViewAdapterTVShowCast.ViewHolder> {
    private ArrayList<Cast> mTvShowCast;
    Context mContext;

    public RecyclerViewAdapterTVShowCast(ArrayList<Cast> tvShowCastMain, Context context) {
        mTvShowCast = tvShowCastMain;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_cast_list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mTvShowCast != null) {
            holder.tvshowCastName.setText(mTvShowCast.get(position).getName());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTvShowCast.get(position).getProfile_path()).into(holder.tvshowCastImage);
            String character = "as " + mTvShowCast.get(position).getCharacter();
            holder.tvshowCastCharacter.setText(character);
        }
    }

    @Override
    public int getItemCount() {
        return mTvShowCast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tvshowCastImage;
        TextView tvshowCastName;
        TextView tvshowCastCharacter;
        View tvshowCastDivider;

        ViewHolder(View itemView) {
            super(itemView);
            tvshowCastImage = (ImageView) itemView.findViewById(R.id.tvshowCastImage);
            tvshowCastName = (TextView) itemView.findViewById(R.id.tvshowCastName);
            tvshowCastCharacter = (TextView) itemView.findViewById(R.id.tvshowCastCharacter);
            tvshowCastDivider = (View) itemView.findViewById(R.id.tvshowCastDivider);

        }
    }
}

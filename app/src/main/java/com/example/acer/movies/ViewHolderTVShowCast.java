package com.example.acer.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KeshavAggarwal on 13/03/17.
 */
public class ViewHolderTVShowCast extends RecyclerView.ViewHolder {
    ImageView tvshowCastImage;
    TextView tvshowCastName;
    TextView tvshowCastCharacter;
    View tvshowCastDivider;

    ViewHolderTVShowCast(View itemView) {
        super(itemView);
        tvshowCastImage = (ImageView) itemView.findViewById(R.id.tvshowCastImage);
        tvshowCastName = (TextView) itemView.findViewById(R.id.tvshowCastName);
        tvshowCastCharacter = (TextView) itemView.findViewById(R.id.tvshowCastCharacter);
        tvshowCastDivider = (View) itemView.findViewById(R.id.tvshowCastDivider);

    }
}
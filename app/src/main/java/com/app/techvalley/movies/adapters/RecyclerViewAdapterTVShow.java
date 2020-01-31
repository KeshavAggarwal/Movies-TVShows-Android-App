package com.app.techvalley.movies.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.techvalley.movies.AboutTVShowActivity;
import com.app.techvalley.movies.utils.IntentConstants;
import com.app.techvalley.movies.OnRecyclerViewItemClickListener;
import com.app.techvalley.movies.R;
import com.app.techvalley.movies.SeeAllTVShowsActivity;
import com.app.techvalley.movies.network.TVShowResponse;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class RecyclerViewAdapterTVShow extends RecyclerView.Adapter<RecyclerViewAdapterTVShow.ViewHolder> implements OnRecyclerViewItemClickListener {

    private TVShowResponse[] mTVShows;
    Context mContext;
    private RecyclerViewAdapterTVShowHorizontal recyclerViewAdapter;

    public RecyclerViewAdapterTVShow(TVShowResponse[] tvShows, Context context) {
        mTVShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acitivity_main_second_tvshows, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (mTVShows != null && mTVShows.length > position) {
            if (getItemViewType(position) == 0) {
                if (mTVShows[position] != null) {
                    holder.tvShowType.setText("Airing Today");
                    holder.seeAlltextView.setText("See all");
                    holder.seeAlltextView.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                        intent.putExtra("ABCD", mTVShows[position].getTvShows());
                        intent.putExtra("TVSHOW_TYPE", "Airing Today");
                        intent.setClass(mContext, SeeAllTVShowsActivity.class);
                        mContext.startActivity(intent, bundle);
                    });
                    recyclerViewAdapter = new RecyclerViewAdapterTVShowHorizontal(mTVShows[position].getTvShows(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 1) {
                if (mTVShows[position] != null) {
                    holder.tvShowType.setText("On Air");
                    holder.seeAlltextView.setText("See all");
                    holder.seeAlltextView.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                        intent.setClass(mContext, SeeAllTVShowsActivity.class);
                        intent.putExtra("ABCD", mTVShows[position].getTvShows());
                        intent.putExtra("TVSHOW_TYPE", "On Air");
                        mContext.startActivity(intent, bundle);

                    });
                    recyclerViewAdapter = new RecyclerViewAdapterTVShowHorizontal(mTVShows[position].getTvShows(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 2) {
                if (mTVShows[position] != null) {
                    holder.tvShowType.setText("Popular Shows");
                    holder.seeAlltextView.setText("See all");
                    holder.seeAlltextView.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                        intent.putExtra("ABCD", mTVShows[position].getTvShows());
                        intent.putExtra("TVSHOW_TYPE", "Popular Shows");
                        intent.setClass(mContext, SeeAllTVShowsActivity.class);
                        mContext.startActivity(intent, bundle);

                    });
                    recyclerViewAdapter = new RecyclerViewAdapterTVShowHorizontal(mTVShows[position].getTvShows(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }
            } else if (getItemViewType(position) == 3) {
                if (mTVShows[position] != null) {
                    holder.tvShowType.setText("Top Rated Shows");
                    holder.seeAlltextView.setText("See all");
                    holder.seeAlltextView.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                        intent.putExtra("ABCD", mTVShows[position].getTvShows());
                        intent.putExtra("TVSHOW_TYPE", "Top Rated Shows");
                        intent.setClass(mContext, SeeAllTVShowsActivity.class);
                        mContext.startActivity(intent, bundle);

                    });
                    recyclerViewAdapter = new RecyclerViewAdapterTVShowHorizontal(mTVShows[position].getTvShows(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this, position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return mTVShows.length;
    }


    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }


    @Override
    public void onRecyclerViewItemClicked(int verticalPosition, int horizontalPosition, View view) {
        Intent intent = new Intent();
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName()).toBundle();
        intent.setClass(mContext, AboutTVShowActivity.class);
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTVShows[verticalPosition].getTvShows().get(horizontalPosition).getId());
        intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTVShows[verticalPosition].getTvShows().get(horizontalPosition).getPosterPath());
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTVShows[verticalPosition].getTvShows().get(horizontalPosition).getTitle());
        mContext.startActivity(intent, bundle);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvShowType;
        RecyclerView horizontalRecyclerView;
        TextView seeAlltextView;


        public ViewHolder(View itemView) {
            super(itemView);
            tvShowType = itemView.findViewById(R.id.tvShowTypeTextView);
            seeAlltextView = itemView.findViewById(R.id.seeAllTextView);
            horizontalRecyclerView = itemView.findViewById(R.id.activityMainRecyclerViewHorizontal);
        }
    }


}


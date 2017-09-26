package com.example.acer.movies.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.movies.AboutTVShowActivity;
import com.example.acer.movies.utils.IntentConstants;
import com.example.acer.movies.OnRecyclerViewitemClicklistener;
import com.example.acer.movies.R;
import com.example.acer.movies.SeeAllTVShowsActivity;
import com.example.acer.movies.network.TVShowResponse;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class RecyclerViewAdapterTVShow extends RecyclerView.Adapter<RecyclerViewAdapterTVShow.ViewHolder> implements OnRecyclerViewitemClicklistener {

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
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD", mTVShows[position].getTvShows());
                            intent.putExtra("TVSHOW_TYPE", "Airing Today");
                            intent.setClass(mContext, SeeAllTVShowsActivity.class);
                            mContext.startActivity(intent,bundle);
                        }
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
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.setClass(mContext, SeeAllTVShowsActivity.class);
                            intent.putExtra("ABCD", mTVShows[position].getTvShows());
                            intent.putExtra("TVSHOW_TYPE", "On Air");
                            mContext.startActivity(intent,bundle);

                        }
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
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD", mTVShows[position].getTvShows());
                            intent.putExtra("TVSHOW_TYPE", "Popular Shows");
                            intent.setClass(mContext, SeeAllTVShowsActivity.class);
                            mContext.startActivity(intent,bundle);

                        }
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
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD", mTVShows[position].getTvShows());
                            intent.putExtra("TVSHOW_TYPE", "Top Rated Shows");
                            intent.setClass(mContext, SeeAllTVShowsActivity.class);
                            mContext.startActivity(intent,bundle);

                        }
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
    public void onRecyclerViewItemClicked(int verticalposition, int horizontalPosition, View view) {
        Intent intent = new Intent();
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,view,view.getTransitionName()).toBundle();
        intent.setClass(mContext, AboutTVShowActivity.class);
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTVShows[verticalposition].getTvShows().get(horizontalPosition).getId());
        intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTVShows[verticalposition].getTvShows().get(horizontalPosition).getPosterPath());
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTVShows[verticalposition].getTvShows().get(horizontalPosition).getTitle());
        mContext.startActivity(intent,bundle);
        //Toast.makeText(mContext,mMovies[verticalposition].getMovies().get(horizontalPosition).id + " is clicked", Toast.LENGTH_SHORT).show();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvShowType;
        RecyclerView horizontalRecyclerView;
        View divider;
        TextView seeAlltextView;


        public ViewHolder(View itemView) {
            super(itemView);
            tvShowType = (TextView) itemView.findViewById(R.id.tvShowTypeTextView);
            seeAlltextView = (TextView) itemView.findViewById(R.id.seeAllTextView);
            horizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.activityMainRecyclerViewHorizontal);
            divider = (View) itemView.findViewById(R.id.activityMainDivider);

        }
    }


}


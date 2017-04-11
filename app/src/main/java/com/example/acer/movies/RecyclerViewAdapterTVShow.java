package com.example.acer.movies;

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

import com.example.acer.movies.Network.TVShowResponse;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class RecyclerViewAdapterTVShow extends RecyclerView.Adapter<ViewHolderTVShow> implements OnRecyclerViewitemClicklistener {

    TVShowResponse[] mTVShows;
    Context mContext;
    RecyclerViewAdapterTVShowHorizontal recyclerViewAdapter;

    public RecyclerViewAdapterTVShow(TVShowResponse[] tvShows, Context context) {
        mTVShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolderTVShow onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acitivity_main_second_tvshows, parent, false);
        ViewHolderTVShow holder = new ViewHolderTVShow(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTVShow holder, final int position) {
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
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTVShows[verticalposition].getTvShows().get(horizontalPosition).id);
        intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTVShows[verticalposition].getTvShows().get(horizontalPosition).posterPath);
        intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTVShows[verticalposition].getTvShows().get(horizontalPosition).title);
        mContext.startActivity(intent,bundle);
        //Toast.makeText(mContext,mMovies[verticalposition].getMovies().get(horizontalPosition).id + " is clicked", Toast.LENGTH_SHORT).show();

    }


}


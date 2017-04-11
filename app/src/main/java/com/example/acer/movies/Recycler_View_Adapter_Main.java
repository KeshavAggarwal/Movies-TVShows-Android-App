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

import com.example.acer.movies.Network.MovieResponse;

/**
 * Created by KeshavAggarwal on 07/01/17.
 */

public class Recycler_View_Adapter_Main extends RecyclerView.Adapter<View_Holder_Main> implements OnRecyclerViewitemClicklistener {

    MovieResponse[] mMovies;
    Context mContext;
    Recycler_View_Adapter recyclerViewAdapter;


    public Recycler_View_Adapter_Main(MovieResponse[] movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public View_Holder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_second, parent, false);
        View_Holder_Main holder = new View_Holder_Main(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder_Main holder, final int position) {
        if (mMovies != null && mMovies.length > position) {
            if (getItemViewType(position) == 0) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Popular Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD",mMovies[position].getMovies());
                            intent.putExtra("MOVIETYPE", "Popular Movies");
                            intent.setClass(mContext,SeeAllMoviesActivity.class);
                            mContext.startActivity(intent, bundle);
                        }
                    });
                    recyclerViewAdapter = new Recycler_View_Adapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this,position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 1) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Now Playing");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.setClass(mContext,SeeAllMoviesActivity.class);
                            intent.putExtra("ABCD",mMovies[position].getMovies());
                            intent.putExtra("MOVIETYPE", "Now Playing");
                            mContext.startActivity(intent,bundle);

                        }
                    });
                    recyclerViewAdapter = new Recycler_View_Adapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this,position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            } else if (getItemViewType(position) == 2) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Top Rated Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD",mMovies[position].getMovies());
                            intent.putExtra("MOVIETYPE", "Top Rated Movies");
                            intent.setClass(mContext,SeeAllMoviesActivity.class);
                            mContext.startActivity(intent,bundle);

                        }
                    });
                    recyclerViewAdapter = new Recycler_View_Adapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this,position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }
            } else if (getItemViewType(position) == 3) {
                if (mMovies[position] != null) {
                    holder.movieType.setText("Upcoming Movies");
                    holder.seeAlltextView.setText("See all >");
                    holder.seeAlltextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
                            intent.putExtra("ABCD",mMovies[position].getMovies());
                            intent.putExtra("MOVIETYPE", "Upcoming Movies");
                            intent.setClass(mContext,SeeAllMoviesActivity.class);
                            mContext.startActivity(intent,bundle);

                        }
                    });
                    recyclerViewAdapter = new Recycler_View_Adapter(mMovies[position].getMovies(), mContext);
                    holder.horizontalRecyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(this,position);

                    LinearLayoutManager horizontalManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    holder.horizontalRecyclerView.setLayoutManager(horizontalManager);
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.length;
    }


    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }


    @Override
    public void onRecyclerViewItemClicked(int verticalposition,int horizontalPosition, View view) {
        Intent intent = new Intent();
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, view, view.getTransitionName()).toBundle();
        //Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle();
        intent.setClass(mContext, AboutMovieActivity.class);
        intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mMovies[verticalposition].getMovies().get(horizontalPosition).id);
        intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mMovies[verticalposition].getMovies().get(horizontalPosition).posterPath);
        intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mMovies[verticalposition].getMovies().get(horizontalPosition).title);
        mContext.startActivity(intent,bundle);
        //Toast.makeText(mContext,mMovies[verticalposition].getMovies().get(horizontalPosition).id + " is clicked", Toast.LENGTH_SHORT).show();

    }



}
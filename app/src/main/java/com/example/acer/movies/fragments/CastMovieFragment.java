package com.example.acer.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.models.Cast;
import com.example.acer.movies.R;
import com.example.acer.movies.adapters.RecyclerViewAdapterMovieCast;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 10/02/17.
 */

public class CastMovieFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapterMovieCast recyclerViewAdapterMovieCast;
    ArrayList<Cast> movieCastMain;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_cast_movie, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.movieCastRecyclerview);
        return v;
    }

    public static CastMovieFragment newInstance() {
        return new CastMovieFragment();
    }

    public void setUIArguements(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieCastMain = (ArrayList<Cast>) args.getSerializable("MOVIE_CAST");

                recyclerViewAdapterMovieCast = new RecyclerViewAdapterMovieCast(movieCastMain, context);
                recyclerView.setAdapter(recyclerViewAdapterMovieCast);

                LinearLayoutManager verticalManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(verticalManager);

            }


        });
    }
}
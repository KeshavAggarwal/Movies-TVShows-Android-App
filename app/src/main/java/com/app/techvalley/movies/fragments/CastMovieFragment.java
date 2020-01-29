package com.app.techvalley.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.techvalley.movies.models.Cast;
import com.app.techvalley.movies.R;
import com.app.techvalley.movies.adapters.RecyclerViewAdapterMovieCast;

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
        recyclerView = v.findViewById(R.id.movieCastRecyclerview);
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
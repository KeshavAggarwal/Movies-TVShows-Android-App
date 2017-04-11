package com.example.acer.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 10/02/17.
 */

public class CastMoviefragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapterMovieCast recyclerViewAdapterMovieCast;
    ArrayList<Cast> movieCastMain;
    Context context;
    //int movie_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        View v = inflater.inflate(R.layout.fragment_cast_movie, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.movieCastRecyclerview);
        return v;
    }

    /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            movie_id = getArguments().getInt("MOVIE_ID");
        }
    }*/

    public static CastMoviefragment newInstance() {
        CastMoviefragment castMoviefragment = new CastMoviefragment();
        return castMoviefragment;
    }

    public void setUIArguements(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieCastMain = (ArrayList<Cast>) args.getSerializable("MOVIE_CAST");

                //recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.vertical_spacing)));

                recyclerViewAdapterMovieCast = new RecyclerViewAdapterMovieCast(movieCastMain, context);
                recyclerView.setAdapter(recyclerViewAdapterMovieCast);

                LinearLayoutManager verticalManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(verticalManager);


            }


        });
    }
}
package com.example.acer.movies;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.acer.movies.Network.ApiService;
import com.example.acer.movies.Network.MovieResponse;
import com.example.acer.movies.Network.URLConstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KeshavAggarwal on 06/02/17.
 */

public class MoviesFragment extends Fragment {
    RecyclerView recyclerView;
    //ArrayList<MovieResponse> movieResponses;
    MovieResponse[] allMovies;
    /*Animation fabOpen, fabClose;
    FloatingActionButton fabSearch, fabMovieSearch, fabTvShowSearch;*/
    Recycler_View_Adapter_Main recyclerViewAdapterMain;
    //boolean isOpen = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.activityMainVerticalRecyclerView);

        /*fabSearch = (FloatingActionButton) getActivity().findViewById(R.id.searchFabButton);
        fabMovieSearch = (FloatingActionButton) getActivity().findViewById(R.id.searchMovieFabButton);
        fabTvShowSearch = (FloatingActionButton) getActivity().findViewById(R.id.searchTvShowFabButton);

        fabOpen = AnimationUtils.loadAnimation(container.getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(container.getContext(), R.anim.fab_close);*/
        allMovies = new MovieResponse[4];

        recyclerViewAdapterMain = new Recycler_View_Adapter_Main(allMovies, container.getContext());
        recyclerView.setAdapter(recyclerViewAdapterMain);

        LinearLayoutManager verticalManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<MovieResponse> call = service.getPopularMovies(URLConstants.API_KEY, 1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> movieList = response.body().getMovies();
                MovieResponse popularMovies = new MovieResponse();
                if (movieList == null) {
                    return;
                }
                popularMovies.setMovies(movieList);
                allMovies[0] = popularMovies;
                recyclerViewAdapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        Call<MovieResponse> call1 = service.getNowPlayingMovies(URLConstants.API_KEY, 1);

        call1.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> movieList = response.body().getMovies();
                MovieResponse nowPlayingMovies = new MovieResponse();
                if (movieList == null) {
                    return;
                }
                nowPlayingMovies.setMovies(movieList);
                //movieResponses.add(nowPlayingMovies);
                allMovies[1] = nowPlayingMovies;
                recyclerViewAdapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        Call<MovieResponse> call2 = service.getTopRatedMovies(URLConstants.API_KEY, 1);

        call2.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> movieList = response.body().getMovies();
                MovieResponse topRatedMovies = new MovieResponse();
                if (movieList == null) {
                    return;
                }
                topRatedMovies.setMovies(movieList);
                //movieResponses.add(topRatedMovies);
                allMovies[2] = topRatedMovies;
                recyclerViewAdapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        Call<MovieResponse> call3 = service.getUpcomingMovies(URLConstants.API_KEY, 1);

        call3.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> movieList = response.body().getMovies();
                MovieResponse upcomingMovies = new MovieResponse();
                if (movieList == null) {
                    return;
                }
                upcomingMovies.setMovies(movieList);
                //movieResponses.add(upcomingMovies);
                allMovies[3] = upcomingMovies;
                recyclerViewAdapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

       /* recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fabSearch.hide();
                else if (dy < 0)
                    fabSearch.show();
            }

        });
*/
        /*fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen) {
                    fabMovieSearch.startAnimation(fabClose);
                    fabTvShowSearch.startAnimation(fabClose);
                    fabMovieSearch.setClickable(false);
                    fabTvShowSearch.setClickable(false);
                    isOpen = false;

                } else {

                    fabMovieSearch.startAnimation(fabOpen);
                    fabTvShowSearch.startAnimation(fabOpen);
                    fabMovieSearch.setClickable(true);
                    fabTvShowSearch.setClickable(true);
                    isOpen = true;
                }
            }
        });*/

        return v;
    }
}

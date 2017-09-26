package com.example.acer.movies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.models.Movie;
import com.example.acer.movies.R;
import com.example.acer.movies.adapters.Recycler_View_Adapter_Main;
import com.example.acer.movies.network.ApiService;
import com.example.acer.movies.network.MovieResponse;
import com.example.acer.movies.network.URLConstants;

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
    MovieResponse[] allMovies;
    Recycler_View_Adapter_Main recyclerViewAdapterMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.activityMainVerticalRecyclerView);
        allMovies = new MovieResponse[4];

        recyclerViewAdapterMain = new Recycler_View_Adapter_Main(allMovies, getActivity());
        recyclerView.setAdapter(recyclerViewAdapterMain);

        LinearLayoutManager verticalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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
                allMovies[3] = upcomingMovies;
                recyclerViewAdapterMain.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return v;
    }
}

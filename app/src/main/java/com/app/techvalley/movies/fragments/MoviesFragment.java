package com.app.techvalley.movies.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.R;
import com.app.techvalley.movies.adapters.RecyclerViewAdapterMain;
import com.app.techvalley.movies.network.ApiService;
import com.app.techvalley.movies.network.MovieResponse;
import com.app.techvalley.movies.network.URLConstants;

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
    private RecyclerView recyclerView;
    private MovieResponse[] allMovies;
    private RecyclerViewAdapterMain recyclerViewAdapterMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = v.findViewById(R.id.activityMainVerticalRecyclerView);
        allMovies = new MovieResponse[4];
        recyclerViewAdapterMain = new RecyclerViewAdapterMain(allMovies, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(recyclerViewAdapterMain);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    }
}

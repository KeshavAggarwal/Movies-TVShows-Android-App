package com.app.techvalley.movies;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.transition.Slide;
import android.view.Gravity;

import com.app.techvalley.movies.adapters.RecyclerViewAdapterSeeAllActivity;
import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.network.ApiService;
import com.app.techvalley.movies.network.MovieResponse;
import com.app.techvalley.movies.network.URLConstants;
import com.app.techvalley.movies.utils.AppUtil;
import com.app.techvalley.movies.utils.EndlessRecyclerViewScrollListener;
import com.app.techvalley.movies.utils.GridSpacingItemDecoration;
import com.app.techvalley.movies.utils.SpacesItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeAllMoviesActivity extends AppCompatActivity {
    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    RecyclerViewAdapterSeeAllActivity adapter;
    ArrayList<Movie> movies;
    String movieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_all_activity_movie);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Slide slide = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);
        getWindow().setAllowEnterTransitionOverlap(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        movies = (ArrayList<Movie>) intent.getSerializableExtra("ABCD");
        movieType = intent.getStringExtra("MOVIETYPE");

        setTitle(movieType);

        recyclerView = findViewById(R.id.seeAllActivityRecyclerViewMovies);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        adapter = new RecyclerViewAdapterSeeAllActivity(movies, this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, AppUtil.dpToPx(this, 16), true));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMoreData(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadMoreData(int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        if (movieType.equals("Popular Movies")) {

            Call<MovieResponse> call = service.getPopularMovies(URLConstants.API_KEY, page);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    //Log.i("ABC2", "FUN");
                    ArrayList<Movie> movieList = response.body().getMovies();
                    if (movieList == null) {
                        return;
                    }
                    for (Movie obj : movieList) {
                        movies.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });
        } else if (movieType.equals("Now Playing")) {
            Call<MovieResponse> call = service.getNowPlayingMovies(URLConstants.API_KEY, page);

            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    ArrayList<Movie> movieList = response.body().getMovies();
                    if (movieList == null) {
                        return;
                    }
                    for (Movie obj : movieList) {
                        movies.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });
        } else if (movieType.equals("Top Rated Movies")) {
            Call<MovieResponse> call = service.getTopRatedMovies(URLConstants.API_KEY, page);

            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    //Log.i("ABC2", "FUN");
                    ArrayList<Movie> movieList = response.body().getMovies();
                    if (movieList == null) {
                        return;
                    }
                    for (Movie obj : movieList) {
                        movies.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });
        } else if (movieType.equals("Upcoming Movies")) {
            Call<MovieResponse> call = service.getUpcomingMovies(URLConstants.API_KEY, page);

            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    ArrayList<Movie> movieList = response.body().getMovies();
                    if (movieList == null) {
                        return;
                    }
                    for (Movie obj : movieList) {
                        movies.add(obj);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                }
            });

        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

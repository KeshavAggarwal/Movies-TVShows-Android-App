package com.example.acer.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;

import com.example.acer.movies.adapters.RecyclerViewAdpterSeeAllActivity;
import com.example.acer.movies.models.Movie;
import com.example.acer.movies.network.ApiService;
import com.example.acer.movies.network.MovieResponse;
import com.example.acer.movies.network.URLConstants;
import com.example.acer.movies.utils.EndlessRecyclerViewScrollListener;
import com.example.acer.movies.utils.SpacesItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeAllMoviesActivity extends AppCompatActivity {
    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    RecyclerViewAdpterSeeAllActivity recyclerViewAdpterSeeAllActivity;
    ArrayList<Movie> movies;
    String movieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_all_activity_movie);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        recyclerView = (RecyclerView) findViewById(R.id.seeAllActivityRecyclerViewMovies);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerViewAdpterSeeAllActivity = new RecyclerViewAdpterSeeAllActivity(movies, this);
        recyclerView.setAdapter(recyclerViewAdpterSeeAllActivity);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadmoreData(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }


    /*PopularMoviesFragment popularMoviesFragment = new PopularMoviesFragment();
    Bundle bundle = new Bundle();
    bundle.putSerializable("ABC", movies);
    popularMoviesFragment.setArguments(bundle);

    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerSeeAllActivity,popularMoviesFragment).commit();*/
    private void loadmoreData(int page) {

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
                    recyclerViewAdpterSeeAllActivity.notifyDataSetChanged();
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
                    recyclerViewAdpterSeeAllActivity.notifyDataSetChanged();
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
                    recyclerViewAdpterSeeAllActivity.notifyDataSetChanged();
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
                    //Log.i("ABC2", "FUN");
                    ArrayList<Movie> movieList = response.body().getMovies();
                    if (movieList == null) {
                        return;
                    }
                    for (Movie obj : movieList) {
                        movies.add(obj);
                    }
                    recyclerViewAdpterSeeAllActivity.notifyDataSetChanged();
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

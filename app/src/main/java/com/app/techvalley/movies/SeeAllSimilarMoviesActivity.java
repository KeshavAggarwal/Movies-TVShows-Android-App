package com.app.techvalley.movies;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.app.techvalley.movies.adapters.RecyclerViewAdapterSeeAllActivity;
import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.network.ApiService;
import com.app.techvalley.movies.network.MovieResponse;
import com.app.techvalley.movies.network.URLConstants;
import com.app.techvalley.movies.utils.EndlessRecyclerViewScrollListener;
import com.app.techvalley.movies.utils.IntentConstants;
import com.app.techvalley.movies.utils.SpacesItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeAllSimilarMoviesActivity extends AppCompatActivity {
    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    ProgressBar progressBarSeeAllActivity;
    RecyclerViewAdapterSeeAllActivity recyclerViewAdpterSeeAllActivity;
    ArrayList<Movie> movies;
    int movie_id;
    String movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_all_activity_movie);

        progressBarSeeAllActivity = findViewById(R.id.progressBarSeeAllActivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Slide slide = new Slide(Gravity.BOTTOM);
        //getWindow().setEnterTransition(slide);
        //getWindow().setAllowEnterTransitionOverlap(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        progressBarSeeAllActivity.setVisibility(View.VISIBLE);
        //movies = (ArrayList<Movie>) intent.getSerializableExtra("ABCD");
        movie_id = intent.getIntExtra(IntentConstants.INTENT_KEY_MOVIE_ID,0);
        movieName = intent.getStringExtra("MOVIE_NAME");
        setTitle("Similar to " + movieName);
        movies = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.seeAllActivityRecyclerViewMovies);


        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerViewAdpterSeeAllActivity = new RecyclerViewAdapterSeeAllActivity(movies, this);
        recyclerView.setAdapter(recyclerViewAdpterSeeAllActivity);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        loadmoreData(1);

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadmoreData(page);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }

    private void loadmoreData(int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<MovieResponse> call = service.getSimilarMovies(movie_id, URLConstants.API_KEY, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBarSeeAllActivity.setVisibility(View.GONE);
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
                progressBarSeeAllActivity.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
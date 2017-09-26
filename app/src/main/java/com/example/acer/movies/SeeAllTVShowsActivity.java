package com.example.acer.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;

import com.example.acer.movies.adapters.RecyclerAdapterSeeAllTvshows;
import com.example.acer.movies.models.TVShow;
import com.example.acer.movies.network.ApiService;
import com.example.acer.movies.network.TVShowResponse;
import com.example.acer.movies.network.URLConstants;
import com.example.acer.movies.utils.EndlessRecyclerViewScrollListener;
import com.example.acer.movies.utils.SpacesItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeAllTVShowsActivity extends AppCompatActivity {

    private EndlessRecyclerViewScrollListener scrollListener;
    RecyclerView recyclerView;
    RecyclerAdapterSeeAllTvshows recyclerAdapterSeeAllTvshows;
    ArrayList<TVShow> tvShows;
    String tvShowType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_all_activity_tvshows);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Slide slide = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);
        getWindow().setAllowEnterTransitionOverlap(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        tvShows = (ArrayList<TVShow>) intent.getSerializableExtra("ABCD");
        tvShowType = intent.getStringExtra("TVSHOW_TYPE");

        setTitle(tvShowType);

        recyclerView = (RecyclerView) findViewById(R.id.seeAllActivityRecyclerViewTVShows);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerAdapterSeeAllTvshows = new RecyclerAdapterSeeAllTvshows(tvShows, this);
        recyclerView.setAdapter(recyclerAdapterSeeAllTvshows);

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

    private void loadmoreData(int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.TVSHOW_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        if (tvShowType.equals("Airing Today")) {

            Call<TVShowResponse> call = service.getAiringToday(URLConstants.API_KEY, page);
            call.enqueue(new Callback<TVShowResponse>() {
                @Override
                public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                    //Log.i("ABC2", "FUN");
                    ArrayList<TVShow> tvShowList = response.body().getTvShows();
                    if (tvShowList == null) {
                        return;
                    }
                    for (TVShow obj : tvShowList) {
                        tvShows.add(obj);
                    }
                    recyclerAdapterSeeAllTvshows.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TVShowResponse> call, Throwable t) {

                }
            });
        } else if (tvShowType.equals("On Air")) {
            Call<TVShowResponse> call = service.getOnAir(URLConstants.API_KEY, page);

            call.enqueue(new Callback<TVShowResponse>() {
                @Override
                public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                    ArrayList<TVShow> tvShowList = response.body().getTvShows();
                    if (tvShowList == null) {
                        return;
                    }
                    for (TVShow obj : tvShowList) {
                        tvShows.add(obj);
                    }
                    recyclerAdapterSeeAllTvshows.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TVShowResponse> call, Throwable t) {

                }
            });
        } else if (tvShowType.equals("Popular Shows")) {
            Call<TVShowResponse> call = service.getPopular(URLConstants.API_KEY, page);

            call.enqueue(new Callback<TVShowResponse>() {
                @Override
                public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                    //Log.i("ABC2", "FUN");
                    ArrayList<TVShow> tvShowList = response.body().getTvShows();
                    if (tvShowList == null) {
                        return;
                    }
                    for (TVShow obj : tvShowList) {
                        tvShows.add(obj);
                    }
                    recyclerAdapterSeeAllTvshows.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TVShowResponse> call, Throwable t) {

                }
            });
        } else if (tvShowType.equals("Top Rated Shows")) {
            Call<TVShowResponse> call = service.getTopRated(URLConstants.API_KEY, page);

            call.enqueue(new Callback<TVShowResponse>() {
                @Override
                public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                    //Log.i("ABC2", "FUN");
                    ArrayList<TVShow> tvShowList = response.body().getTvShows();
                    if (tvShowList == null) {
                        return;
                    }
                    for (TVShow obj : tvShowList) {
                        tvShows.add(obj);
                    }
                    recyclerAdapterSeeAllTvshows.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TVShowResponse> call, Throwable t) {

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






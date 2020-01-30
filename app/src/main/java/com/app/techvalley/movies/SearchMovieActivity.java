package com.app.techvalley.movies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.app.techvalley.movies.adapters.MoviesSearchAdapter;
import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.network.ApiService;
import com.app.techvalley.movies.network.MovieResponse;
import com.app.techvalley.movies.network.URLConstants;
import com.app.techvalley.movies.utils.EndlessRecyclerViewScrollListener;
import com.app.techvalley.movies.utils.SpacesItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMovieActivity extends AppCompatActivity {
    private EndlessRecyclerViewScrollListener scrollListener;
    android.widget.SearchView searchView;
    ImageButton searchBack;
    ProgressBar progress;
    MoviesSearchAdapter adapter;
    RecyclerView searchResultsRV;
    ArrayList<Movie> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("Search Movies");
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        progress = findViewById(R.id.progressBar);
        searchResultsRV = findViewById(R.id.searchResultsRV);
        searchBack = findViewById(R.id.searchback);

        data = new ArrayList<>();

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        searchResultsRV.setLayoutManager(gridLayoutManager);
        searchResultsRV.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        adapter = new MoviesSearchAdapter(data, this);
        searchResultsRV.setAdapter(adapter);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                searchFor(query, 1);
                scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        searchFor(query, page);

                    }
                };
                searchResultsRV.addOnScrollListener(scrollListener);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchResultsRV.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                data.clear();
                return true;
            }
        });
    }

    private void searchFor(String query, int page) {
        if (page == 1)
            progress.setVisibility(View.VISIBLE);
        searchResultsRV.setVisibility(View.VISIBLE);
        searchView.clearFocus();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<MovieResponse> call = service.getSearchedMovies(URLConstants.API_KEY, query, page);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progress.setVisibility(View.GONE);
                ArrayList<Movie> searchMovieList = response.body().getMovies();
                if (searchMovieList == null) {
                    return;
                }
                for (Movie obj : searchMovieList) {
                    data.add(obj);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        searchBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (!TextUtils.isEmpty(query)) {
                searchView.setQuery(query, false);
                searchFor(query, 1);
            }

        }
    }

}

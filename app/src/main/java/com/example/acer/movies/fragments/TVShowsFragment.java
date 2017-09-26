package com.example.acer.movies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.movies.R;
import com.example.acer.movies.models.TVShow;
import com.example.acer.movies.adapters.RecyclerViewAdapterTVShow;
import com.example.acer.movies.network.ApiService;
import com.example.acer.movies.network.TVShowResponse;
import com.example.acer.movies.network.URLConstants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KeshavAggarwal on 07/02/17.
 */

public class TVShowsFragment extends Fragment {
    RecyclerView recyclerView;
    TVShowResponse[] allTvShows;
    RecyclerViewAdapterTVShow recyclerViewAdapterTVShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tvshows, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.activityMainVerticalRecyclerView);
        allTvShows = new TVShowResponse[4];

        recyclerViewAdapterTVShow = new RecyclerViewAdapterTVShow(allTvShows, getActivity());
        recyclerView.setAdapter(recyclerViewAdapterTVShow);

        LinearLayoutManager verticalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.TVSHOW_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<TVShowResponse> call = service.getAiringToday(URLConstants.API_KEY, 1);

        call.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                ArrayList<TVShow> tvShowList = response.body().getTvShows();
                TVShowResponse airingtoday = new TVShowResponse();
                if (tvShowList == null) {
                    return;
                }
                airingtoday.setTvShows(tvShowList);
                allTvShows[0] = airingtoday;
                recyclerViewAdapterTVShow.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

        Call<TVShowResponse> call1 = service.getOnAir(URLConstants.API_KEY, 1);

        call1.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                ArrayList<TVShow> tvShowList = response.body().getTvShows();
                TVShowResponse onAir = new TVShowResponse();
                if (tvShowList == null) {
                    return;
                }
                onAir.setTvShows(tvShowList);
                allTvShows[1] = onAir;
                recyclerViewAdapterTVShow.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

        Call<TVShowResponse> call2 = service.getPopular(URLConstants.API_KEY, 1);

        call2.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                ArrayList<TVShow> tvShowList = response.body().getTvShows();
                TVShowResponse popular = new TVShowResponse();
                if (tvShowList == null) {
                    return;
                }
                popular.setTvShows(tvShowList);
                allTvShows[2] = popular;
                recyclerViewAdapterTVShow.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

        Call<TVShowResponse> call3 = service.getTopRated(URLConstants.API_KEY, 1);

        call3.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                ArrayList<TVShow> tvShowList = response.body().getTvShows();
                TVShowResponse topRated = new TVShowResponse();
                if (tvShowList == null) {
                    return;
                }
                topRated.setTvShows(tvShowList);
                allTvShows[3] = topRated;
                recyclerViewAdapterTVShow.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

        return v;
    }
}



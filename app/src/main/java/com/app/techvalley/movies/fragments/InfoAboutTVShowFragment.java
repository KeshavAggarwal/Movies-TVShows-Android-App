package com.app.techvalley.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.techvalley.movies.R;
import com.app.techvalley.movies.models.TVShowsCreaters;
import com.app.techvalley.movies.models.Trailer;
import com.app.techvalley.movies.adapters.RecyclerAdapterTVShowTrailer;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 09/02/17.
 */

public class InfoAboutTVShowFragment extends Fragment {

    TextView aboutTvShowTextView;
    TextView firstAirDateTextView;
    TextView lastAirDateTextView;
    TextView createdByTextView;
    TextView showTypeTextView;
    TextView showStatusTextView;
    TextView noReviewTextView;
    ArrayList<Trailer> mainTrailerTvShowsThumbnails;
    RecyclerView trailorsRecyclerView;
    Context context;
    RecyclerAdapterTVShowTrailer recyclerAdapterTVShowTrailer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_tvshow, container, false);
        context = getActivity();
        aboutTvShowTextView = v.findViewById(R.id.aboutTvShowTextView);
        firstAirDateTextView = v.findViewById(R.id.firstAirDateTextView);
        lastAirDateTextView = v.findViewById(R.id.lastAirDateTextView);
        createdByTextView = v.findViewById(R.id.createdByTextView);
        showStatusTextView = v.findViewById(R.id.showStatusTextView);
        showTypeTextView = v.findViewById(R.id.showTypeTextView);
        noReviewTextView = v.findViewById(R.id.noTVTrailerTextView);
        trailorsRecyclerView = v.findViewById(R.id.trailorsTvShowRecyclerView);

        return v;
    }


    public static InfoAboutTVShowFragment newInstance() {
        InfoAboutTVShowFragment infoAboutTVShowFragment = new InfoAboutTVShowFragment();
        return infoAboutTVShowFragment;
    }

    public void setUIArguements(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                aboutTvShowTextView.setText(args.getString("OVERVIEW"));
                String firstAirDate = dateGenerator(args.getString("FIRST_AIR_DATE"));
                firstAirDateTextView.setText(firstAirDate);
                String lastAirDate = dateGenerator(args.getString("LAST_AIR_DATE"));
                lastAirDateTextView.setText(lastAirDate);
                ArrayList<TVShowsCreaters> obj = (ArrayList<TVShowsCreaters>) args.getSerializable("CREATORS");
                for (int i = 0; i < obj.size(); i++) {
                    if (i < obj.size() - 1)
                        createdByTextView.append(obj.get(i).getName() + ", ");
                    else
                        createdByTextView.append(obj.get(i).getName());

                }
                showTypeTextView.setText(args.getString("SHOW_TYPE"));
                showStatusTextView.setText(args.getString("STATUS"));

                mainTrailerTvShowsThumbnails = (ArrayList<Trailer>) args.getSerializable("TRAILER_THUMBNAILS");
                if (mainTrailerTvShowsThumbnails.size() == 0) {
                    noReviewTextView.setVisibility(View.VISIBLE);
                    noReviewTextView.setText("No Trailers are currently available.");
                } else {

                    recyclerAdapterTVShowTrailer = new RecyclerAdapterTVShowTrailer(mainTrailerTvShowsThumbnails, context);
                    trailorsRecyclerView.setAdapter(recyclerAdapterTVShowTrailer);

                    LinearLayoutManager HorizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    trailorsRecyclerView.setLayoutManager(HorizontalManager);
                }

            }
        });

    }

    private String dateGenerator(String date) {
        String month = date.substring(5, 7);
        String ans = "";
        switch (month) {
            case "01":
                ans = "January";
                break;
            case "02":
                ans = "February";
                break;
            case "03":
                ans = "March";
                break;
            case "04":
                ans = "April";
                break;
            case "05":
                ans = "May";
                break;
            case "06":
                ans = "June";
                break;
            case "07":
                ans = "July";
                break;
            case "08":
                ans = "August";
                break;
            case "09":
                ans = "September";
                break;
            case "10":
                ans = "October";
                break;
            case "11":
                ans = "November";
                break;
            case "12":
                ans = "December";
                break;

        }
        return (ans + " " + date.substring(8, 10) + "," + " " + date.substring(0, 4));
    }
}



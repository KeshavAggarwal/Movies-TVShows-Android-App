package com.example.acer.movies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.movies.models.Movie;
import com.example.acer.movies.R;
import com.example.acer.movies.models.Trailer;
import com.example.acer.movies.adapters.RecyclerAdapterMovieTrailer;
import com.example.acer.movies.adapters.RecyclerAdapterSimilarMovies;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 24/01/17.
 */

public class InfoAboutMovieFragment extends Fragment {

    TextView abouFilmTextView;
    TextView releasedTextView;
    TextView budgetTextView;
    TextView seeAlltextViewMovieInfofragment;
    TextView noReviewMovieTextView;
    TextView noSimilarMoviesTextView;
    TextView revenueTextView;
    RecyclerView trailorsRecyclerView;
    RecyclerView similarMoviesRecyclerView;
    RecyclerAdapterMovieTrailer recyclerAdapterMovieTrailer;
    RecyclerAdapterSimilarMovies recyclerAdapterSimilarMovies;
    Context context;
    ArrayList<Movie> mainSimilarMovies;
    ArrayList<Trailer> mainTrailerMoviesThumbnails;
    InfoAboutMovieFragmentListener infoAboutMovieFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        infoAboutMovieFragmentListener = (InfoAboutMovieFragmentListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface InfoAboutMovieFragmentListener {
        void onSeeAllSimilarMoviesClicked();
    }

    public void setInfoAboutMovieFragmentListener(InfoAboutMovieFragmentListener listener) {
        this.infoAboutMovieFragmentListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View v = inflater.inflate(R.layout.fragment_info_movie, container, false);
        abouFilmTextView = (TextView) v.findViewById(R.id.aboutFilmTextView);
        releasedTextView = (TextView) v.findViewById(R.id.releasedTextView);
        budgetTextView = (TextView) v.findViewById(R.id.budgetTextView);
        noReviewMovieTextView = (TextView) v.findViewById(R.id.noReviewMovieTextView);
        revenueTextView = (TextView) v.findViewById(R.id.revenueTextView);
        noSimilarMoviesTextView = (TextView) v.findViewById(R.id.noSimilarMoviesTextView);
        trailorsRecyclerView = (RecyclerView) v.findViewById(R.id.trailorsRecyclerView);
        similarMoviesRecyclerView = (RecyclerView) v.findViewById(R.id.similarMoviesRecyclerView);
        seeAlltextViewMovieInfofragment = (TextView) v.findViewById(R.id.seeAllTextViewMovieInfoFragment);
        seeAlltextViewMovieInfofragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoAboutMovieFragmentListener.onSeeAllSimilarMoviesClicked();
            }
        });

        return v;
    }


    public static InfoAboutMovieFragment newInstance() {
        return new InfoAboutMovieFragment();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setUIArguements(final Bundle args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (args.getBoolean("INFO")) {
                    abouFilmTextView.setText(args.getString("OVERVIEW"));
                    String releaseDate = dateGenerator(args.getString("RELEASE_DATE"));
                    releasedTextView.setText(releaseDate);
                    String budget = "£" + String.valueOf(args.getLong("BUDGET"));
                    budgetTextView.setText(budget);
                    String revenue = "£" + String.valueOf(args.getLong("REVENUE"));
                    revenueTextView.setText(revenue);
                    mainTrailerMoviesThumbnails = (ArrayList<Trailer>) args.getSerializable("TRAILER_THUMBNAILS");
                    if (mainTrailerMoviesThumbnails.size() == 0) {
                        noReviewMovieTextView.setVisibility(View.VISIBLE);
                        noReviewMovieTextView.setText("No Trailers are currently available.");
                    } else {
                        recyclerAdapterMovieTrailer = new RecyclerAdapterMovieTrailer(mainTrailerMoviesThumbnails, context);
                        trailorsRecyclerView.setAdapter(recyclerAdapterMovieTrailer);

                        LinearLayoutManager HorizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                        trailorsRecyclerView.setLayoutManager(HorizontalManager);

                    }

                } else if (args.getBoolean("SIMILAR")) {
                    mainSimilarMovies = (ArrayList<Movie>) args.getSerializable("SIMILAR_MOVIES");
                    if (mainSimilarMovies.size() == 0) {
                        noSimilarMoviesTextView.setVisibility(View.VISIBLE);
                        noSimilarMoviesTextView.setText("No Similar Movies are currently available.");
                    } else {
                        recyclerAdapterSimilarMovies = new RecyclerAdapterSimilarMovies(mainSimilarMovies, context);
                        similarMoviesRecyclerView.setAdapter(recyclerAdapterSimilarMovies);

                        LinearLayoutManager HorizontalManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                        similarMoviesRecyclerView.setLayoutManager(HorizontalManager1);
                    }
                }

            }


        });
    }

    private String dateGenerator(String date) {
        if (date.length() == 9 || date.length() == 10) {
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
        } else
            return "NA";
    }
}

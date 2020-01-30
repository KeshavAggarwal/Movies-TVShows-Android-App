package com.app.techvalley.movies;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.app.techvalley.movies.adapters.MainFragmentPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager containerVP;
    Animation fabOpen, fabClose;
    View translucentV;
    FloatingActionButton searchFB, fabMovieSearch, fabTvShowSearch;
    TextView searchMovieTV, searchShowTV;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MovieDB");
        translucentV = findViewById(R.id.translucentV);

        searchFB = findViewById(R.id.searchFAB);
        fabMovieSearch = findViewById(R.id.searchMovieFabButton);
        fabTvShowSearch = findViewById(R.id.searchTvShowFabButton);
        searchMovieTV = findViewById(R.id.searchMovieTV);
        searchShowTV = findViewById(R.id.searchShowTV);
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        TabLayout mainTL = findViewById(R.id.mainTL);
        mainTL.addTab(mainTL.newTab());
        mainTL.addTab(mainTL.newTab());

        MainFragmentPager mainFragmentPager = new MainFragmentPager(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        containerVP = findViewById(R.id.containerVP);
        containerVP.setAdapter(mainFragmentPager);
        mainTL.setupWithViewPager(containerVP);

        mainTL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                containerVP.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        searchFB.setOnClickListener(v -> {
            if (isOpen) {
                translucentV.setVisibility(View.GONE);
                searchFB.setImageResource(R.drawable.fab_search);
                fabMovieSearch.startAnimation(fabClose);
                fabTvShowSearch.startAnimation(fabClose);
                searchMovieTV.setVisibility(View.INVISIBLE);
                searchShowTV.setVisibility(View.INVISIBLE);
                searchMovieTV.startAnimation(fabClose);
                searchShowTV.startAnimation(fabClose);
                fabMovieSearch.setClickable(false);
                fabTvShowSearch.setClickable(false);
                isOpen = false;

            } else {
                translucentV.setVisibility(View.VISIBLE);
                searchFB.setImageResource(R.drawable.ic_close);
                fabMovieSearch.startAnimation(fabOpen);
                fabTvShowSearch.startAnimation(fabOpen);
                searchMovieTV.setVisibility(View.VISIBLE);
                searchShowTV.setVisibility(View.VISIBLE);
                searchMovieTV.startAnimation(fabOpen);
                searchShowTV.startAnimation(fabOpen);
                fabMovieSearch.setClickable(true);
                fabTvShowSearch.setClickable(true);
                isOpen = true;
            }
        });

        fabMovieSearch.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SearchMovieActivity.class);
            startActivity(intent);
        });

        fabTvShowSearch.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SearchTVShowsActivity.class);
            startActivity(intent);
        });
    }


}

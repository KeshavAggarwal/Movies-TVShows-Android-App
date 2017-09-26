package com.example.acer.movies;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.acer.movies.adapters.MainFragmentPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    Animation fabOpen, fabClose;
    View translucentView;
    FloatingActionButton fabSearch, fabMovieSearch, fabTvShowSearch;
    TextView searchMovieTextView, searchShowTextView;
    boolean isOpen = false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MovieDB");

        translucentView = (View) findViewById(R.id.translucentView);

        fabSearch = (FloatingActionButton) findViewById(R.id.searchFabButton);
        fabMovieSearch = (FloatingActionButton) findViewById(R.id.searchMovieFabButton);
        fabTvShowSearch = (FloatingActionButton) findViewById(R.id.searchTvShowFabButton);

        searchMovieTextView = (TextView) findViewById(R.id.searchMovieTextView);
        searchShowTextView = (TextView) findViewById(R.id.searchShowTextView);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        //MoviesFragment moviesFragment = new MoviesFragment();
        //Bundle bundle = new Bundle();
        //bundle.putString(IntentConstants.INTENT_KEY_BATCH_NAME,b.getName());
        // studentListFragment.setArguments(bundle);
        //getSupportFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, moviesFragment).commit();

        TabLayout mainTabLayout = (TabLayout) findViewById(R.id.mainTabs);

        mainTabLayout.addTab(mainTabLayout.newTab());
        mainTabLayout.addTab(mainTabLayout.newTab());

        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        MainFragmentPager mainFragmentPager = new MainFragmentPager(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mainFragmentPager);
        mainTabLayout.setupWithViewPager(mViewPager);

        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOpen) {
                    translucentView.setVisibility(View.GONE);
                    fabSearch.setImageResource(R.drawable.fab_search);
                    fabMovieSearch.startAnimation(fabClose);
                    fabTvShowSearch.startAnimation(fabClose);
                    searchMovieTextView.setVisibility(View.INVISIBLE);
                    searchShowTextView.setVisibility(View.INVISIBLE);
                    searchMovieTextView.startAnimation(fabClose);
                    searchShowTextView.startAnimation(fabClose);
                    fabMovieSearch.setClickable(false);
                    fabTvShowSearch.setClickable(false);
                    isOpen = false;

                } else {
                    translucentView.setVisibility(View.VISIBLE);
                    fabSearch.setImageResource(R.drawable.ic_close);
                    fabMovieSearch.startAnimation(fabOpen);
                    fabTvShowSearch.startAnimation(fabOpen);
                    searchMovieTextView.setVisibility(View.VISIBLE);
                    searchShowTextView.setVisibility(View.VISIBLE);
                    searchMovieTextView.startAnimation(fabOpen);
                    searchShowTextView.startAnimation(fabOpen);
                    fabMovieSearch.setClickable(true);
                    fabTvShowSearch.setClickable(true);
                    isOpen = true;
                }
            }
        });

        fabMovieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchMovieActivity.class);
                startActivity(intent);
            }
        });

        fabTvShowSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchTVShowsAcitivity.class);
                startActivity(intent);
            }
        });
    }


}

package com.app.techvalley.movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.techvalley.movies.adapters.BannerViewPagerAdapter;
import com.app.techvalley.movies.adapters.FragmentPager;
import com.app.techvalley.movies.fragments.CastMovieFragment;
import com.app.techvalley.movies.fragments.InfoAboutMovieFragment;
import com.app.techvalley.movies.fragments.ReviewsFragment;
import com.app.techvalley.movies.models.BackdropImage;
import com.app.techvalley.movies.models.Cast;
import com.app.techvalley.movies.models.Genre;
import com.app.techvalley.movies.models.Movie;
import com.app.techvalley.movies.models.Review;
import com.app.techvalley.movies.models.Video;
import com.app.techvalley.movies.network.AboutMovieResponse;
import com.app.techvalley.movies.network.ApiService;
import com.app.techvalley.movies.network.CreditResponse;
import com.app.techvalley.movies.network.ImageResponse;
import com.app.techvalley.movies.network.MovieResponse;
import com.app.techvalley.movies.network.ReviewResponse;
import com.app.techvalley.movies.network.URLConstants;
import com.app.techvalley.movies.utils.IntentConstants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AboutMovieActivity extends AppCompatActivity implements InfoAboutMovieFragment.InfoAboutMovieFragmentListener {

    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private ArrayList<String> allBannerImageFullLinks;
    ImageView poster;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    int currentpage = 0;
    private FragmentPager fragmentPager = new FragmentPager(getSupportFragmentManager());
    TextView movieNameTextView;
    TextView genreTextView;
    TextView releaseDateTextView;
    TextView runTimeTextView;
    Video obj;
    boolean doFirst = true;
    RadioGroup radioGroup;
    int movie_id;
    String movieName;
    ArrayList<Movie> mainSimilarMovies = new ArrayList<>();
    AboutMovieResponse aboutMovieResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie);
        setTitle("");

        Intent intent = getIntent();

        movie_id = intent.getIntExtra(IntentConstants.INTENT_KEY_MOVIE_ID, 0);
        final String posterPath = intent.getStringExtra(IntentConstants.INTENT_KEY_POSTER_PATH);
        movieName = intent.getStringExtra(IntentConstants.INTENT_KEY_MOVIE_NAME);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InfoAboutMovieFragment infoAboutMovieFragment = (InfoAboutMovieFragment) fragmentPager.function(0);
        infoAboutMovieFragment.setInfoAboutMovieFragmentListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        //mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        allBannerImageFullLinks = new ArrayList<>();

        // Set up the ViewPager with the sections adapter.
        //mViewPager.setAdapter(mSectionsPagerAdapter);

        ViewPager mBannerViewPager = findViewById(R.id.pager);
        radioGroup = findViewById(R.id.radioGroup);

        bannerViewPagerAdapter = new BannerViewPagerAdapter(this, allBannerImageFullLinks);
        mBannerViewPager.setAdapter(bannerViewPagerAdapter);

        mBannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (currentpage == 0 && doFirst) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(0);
                    rb.setButtonDrawable(R.drawable.ic_radio_button_checked);
                    doFirst = false;
                }

            }

            @Override
            public void onPageSelected(int position) {
                if (position > currentpage) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
                    rb.setButtonDrawable(R.drawable.ic_radio_button_checked);
                    RadioButton rbi = (RadioButton) radioGroup.getChildAt(currentpage);
                    rbi.setButtonDrawable(R.drawable.ic_radio_button_unchecked);
                    currentpage = position;
                } else {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
                    rb.setButtonDrawable(R.drawable.ic_radio_button_checked);
                    RadioButton rbi = (RadioButton) radioGroup.getChildAt(currentpage);
                    rbi.setButtonDrawable(R.drawable.ic_radio_button_unchecked);
                    currentpage = position;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        poster = findViewById(R.id.posterWithBannerImageView);
        Picasso.get().load(URLConstants.IMAGE_BASE_URL + posterPath).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        int color = palette.getDarkMutedColor(Color.parseColor("#424242"));
                        //Palette.Swatch swatch1 = palette.getDarkVibrantSwatch();
                        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
                        collapsingToolbarLayout.setBackgroundColor(color);
                        collapsingToolbarLayout.setContentScrimColor(color);
                        tabLayout.setBackgroundColor(palette.getMutedColor(Color.parseColor("#424242")));
                    }
                });

                poster.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


        movieNameTextView = findViewById(R.id.nameTextView);
        movieNameTextView.setText(movieName);
        genreTextView = findViewById(R.id.genreTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        runTimeTextView = findViewById(R.id.runTimeTextView);

        tabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.container);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentPager = new FragmentPager(getSupportFragmentManager());

        mViewPager.setAdapter(fragmentPager);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setOffscreenPageLimit(3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.MOVIE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ImageResponse> call = service.getBannerImages(movie_id, URLConstants.API_KEY);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ArrayList<BackdropImage> bannerImagesLinksList = response.body().getBannerImageLinks();
                if (bannerImagesLinksList == null) {
                    return;
                }
                for (int i = 0; i < bannerImagesLinksList.size(); i++) {
                    if (i < 8) {
                        allBannerImageFullLinks.add(URLConstants.BANNER_BASE_URL + bannerImagesLinksList.get(i).getBannerImageLink());
                        RadioButton radioButton = new RadioButton(getApplicationContext());
                        radioButton.setButtonDrawable(R.drawable.ic_radio_button_unchecked);
                        radioGroup.addView(radioButton);
                    } else
                        break;
                }
                bannerViewPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });


        Call<AboutMovieResponse> call1 = service.getAboutMovie(movie_id, URLConstants.API_KEY, "videos");
        call1.enqueue(new Callback<AboutMovieResponse>() {
            @Override
            public void onResponse(Call<AboutMovieResponse> call, Response<AboutMovieResponse> response) {
                ArrayList<Genre> genres = response.body().getGenres();
                for (int i = 0; i < genres.size(); i++) {
                    if (i < genres.size() - 1)
                        genreTextView.append(genres.get(i).getName() + ", ");
                    else
                        genreTextView.append(genres.get(i).getName());
                }

                aboutMovieResponse = new AboutMovieResponse();

                aboutMovieResponse.setOverview(response.body().getOverview());
                aboutMovieResponse.setReleaseDate(response.body().getReleaseDate());
                aboutMovieResponse.setRunTimeOfMovie(response.body().getRunTimeOfMovie());
                aboutMovieResponse.setBudget(response.body().getBudget());
                aboutMovieResponse.setRevenue(response.body().getRevenue());
                aboutMovieResponse.setGenres(response.body().getGenres());
                aboutMovieResponse.setVideo(response.body().getVideo());

                obj = response.body().getVideo();
                obj.setTrailers(obj.getTrailers());
                if (aboutMovieResponse.getReleaseDate().length() >= 5)
                    releaseDateTextView.setText(aboutMovieResponse.getReleaseDate().substring(0, 4));
                runTimeTextView.setText(aboutMovieResponse.getRunTimeOfMovie() / 60 + "hrs " + aboutMovieResponse.getRunTimeOfMovie() % 60 + "mins");

                Bundle bundle = new Bundle();
                bundle.putBoolean("INFO", true);
                bundle.putString("OVERVIEW", aboutMovieResponse.getOverview());
                bundle.putString("RELEASE_DATE", aboutMovieResponse.getReleaseDate());
                bundle.putLong("BUDGET", aboutMovieResponse.getBudget());
                bundle.putLong("REVENUE", aboutMovieResponse.getRevenue());
                bundle.putSerializable("TRAILER_THUMBNAILS", obj.getTrailers());

                InfoAboutMovieFragment obj1 = (InfoAboutMovieFragment) fragmentPager.function(0);
                obj1.setUIArguements(bundle);

            }

            @Override
            public void onFailure(Call<AboutMovieResponse> call, Throwable t) {

            }
        });


        Call<MovieResponse> call2 = service.getSimilarMovies(movie_id, URLConstants.API_KEY, 1);
        call2.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                ArrayList<Movie> similarMoviesList = response.body().getMovies();
                if (similarMoviesList == null) {
                    return;
                }

                for (Movie object : similarMoviesList) {
                    mainSimilarMovies.add(object);
                }

                Bundle bundle = new Bundle();
                bundle.putBoolean("SIMILAR", true);
                bundle.putSerializable("SIMILAR_MOVIES", similarMoviesList);

                InfoAboutMovieFragment obj1 = (InfoAboutMovieFragment) fragmentPager.function(0);
                obj1.setUIArguements(bundle);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        Call<CreditResponse> call3 = service.getCredits(movie_id, URLConstants.API_KEY);
        call3.enqueue(new Callback<CreditResponse>() {
            @Override
            public void onResponse(Call<CreditResponse> call, Response<CreditResponse> response) {
                ArrayList<Cast> movieCast = response.body().getCast();
                if (movieCast == null) {
                    return;
                }

                Bundle args = new Bundle();
                args.putSerializable("MOVIE_CAST", movieCast);
                CastMovieFragment obj = (CastMovieFragment) fragmentPager.function(1);
                obj.setUIArguements(args);

            }

            @Override
            public void onFailure(Call<CreditResponse> call, Throwable t) {

            }
        });

        Call<ReviewResponse> call4 = service.getReviews(movie_id, URLConstants.API_KEY);
        call4.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                ArrayList<Review> reviews = response.body().getReviews();
                if (reviews == null) {
                    return;
                }
                Bundle arguements = new Bundle();
                arguements.putSerializable("REVIEWS", reviews);
                ReviewsFragment object = (ReviewsFragment) fragmentPager.function(2);
                object.setUIArguements(arguements);
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onSeeAllSimilarMoviesClicked() {
        Intent intent = new Intent();
        intent.setClass(AboutMovieActivity.this, SeeAllSimilarMoviesActivity.class);
        intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, movie_id);
        intent.putExtra("MOVIE_NAME", movieName);
        startActivity(intent);

    }
}



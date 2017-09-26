package com.example.acer.movies.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KeshavAggarwal on 12/01/17.
 */

public interface ApiService {

    @Headers("access_token: 88484499033")
    @GET("popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key,
                                         @Query("page") int page);

    @GET("now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String api_key,
                                            @Query("page") int page);

    @GET("top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key,
                                          @Query("page") int page);

    @GET("upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key,
                                          @Query("page") int page);

    @GET("{id}/images")
    Call<ImageResponse> getBannerImages(@Path("id") int id, @Query("api_key") String api_key);

    @GET("{id}")
    Call<AboutMovieResponse> getAboutMovie(@Path("id") int id, @Query("api_key") String api_key,
                                           @Query("append_to_response") String videos);

    @GET("{id}/credits")
    Call<CreditResponse> getCredits(@Path("id") int id, @Query("api_key") String api_key);

    @GET("{id}/similar")
    Call<MovieResponse> getSimilarMovies(@Path("id") int id, @Query("api_key") String api_key, @Query("page") int page);

    @GET("{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") int id, @Query("api_key") String api_key);

    @GET("movie")
    Call<MovieResponse> getSearchedMovies(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

    //TV Shows
    @GET("airing_today")
    Call<TVShowResponse> getAiringToday(@Query("api_key") String api_key,
                                        @Query("page") int page);

    @GET("on_the_air")
    Call<TVShowResponse> getOnAir(@Query("api_key") String api_key,
                                  @Query("page") int page);

    @GET("popular")
    Call<TVShowResponse> getPopular(@Query("api_key") String api_key,
                                    @Query("page") int page);

    @GET("top_rated")
    Call<TVShowResponse> getTopRated(@Query("api_key") String api_key,
                                     @Query("page") int page);

    @GET("{id}")
    Call<AboutTVShowResponse> getAboutTVShow(@Path("id") int id, @Query("api_key") String api_key, @Query("append_to_response") String videos);

    @GET("{id}/credits")
    Call<CreditResponse> getTvShowCredits(@Path("id") int id, @Query("api_key") String api_key);

    @GET("tv")
    Call<TVShowResponse> getSearchedShows(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);


}

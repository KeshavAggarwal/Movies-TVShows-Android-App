package com.example.acer.movies;

import android.os.AsyncTask;

import com.example.acer.movies.models.AboutFilm;
import com.example.acer.movies.network.URLConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Acer on 9/14/2016.
 */
public class AboutFilmAsyncTask extends AsyncTask<String, Void, AboutFilm> {

    public interface AboutFilmAsyncTaskListener{
        void onAboutFilmDataFetched(AboutFilm aboutFilm);
    }

    AboutFilmAsyncTaskListener listener;


    public void setListener(AboutFilmAsyncTaskListener listener){
        this.listener = listener;
    }

    @Override
    protected AboutFilm doInBackground(String... strings) {
        String urlString = strings[0];
        StringBuffer output = new StringBuffer("");

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            Scanner scn = new Scanner(inputStream);

            while (scn.hasNext()) {
                output.append(scn.nextLine());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getAboutFilm(output.toString());


    }

    private AboutFilm getAboutFilm(String jsonstring) {
        AboutFilm aboutFilms = null ;

        try {
            JSONObject object = new JSONObject(jsonstring);

            aboutFilms = new AboutFilm();
            aboutFilms.title = object.getString("original_title");
            aboutFilms.rating = object.getDouble("vote_average");
            boolean certificate = object.getBoolean("adult");
            if (certificate == false) {
                aboutFilms.certificate = "UA";
            } else {
                aboutFilms.certificate = "A";
            }
            aboutFilms.overview = object.getString("overview");
            String backdropPath = object.getString("backdrop_path");
            aboutFilms.backgroundPath = URLConstants.BANNER_BASE_URL + backdropPath;
            String poster = object.getString("poster_path");
            aboutFilms.posterPath = URLConstants.IMAGE_BASE_URL + poster;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aboutFilms;
    }

    @Override
    protected void onPostExecute(AboutFilm aboutFilm) {
        if(listener != null){
            listener.onAboutFilmDataFetched(aboutFilm);
        }
    }
}

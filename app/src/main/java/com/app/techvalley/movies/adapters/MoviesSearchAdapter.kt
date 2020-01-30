package com.app.techvalley.movies.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.app.techvalley.movies.AboutMovieActivity
import com.app.techvalley.movies.utils.IntentConstants
import com.app.techvalley.movies.models.Movie
import com.app.techvalley.movies.R
import com.app.techvalley.movies.network.URLConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_movie_list_item.view.*

import java.util.ArrayList

/**
 * Created by KeshavAggarwal on 30/03/17.
 */

class MoviesSearchAdapter(private val mSearchedMovies: ArrayList<Movie>, internal var mContext: Context) : RecyclerView.Adapter<MoviesSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_movie_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.nameTV.text = mSearchedMovies[position].title
        Picasso.get().load(URLConstants.IMAGE_BASE_URL + mSearchedMovies[position].posterPath).into(holder.itemView.thumbnailIV)
        if (mSearchedMovies[position].date.length >= 5) {
            val date = mSearchedMovies[position].date.substring(0, 4)
            holder.itemView.releaseDateTV.text = date
        }
        val rating = java.lang.Double.toString(mSearchedMovies[position].rating)
        holder.itemView.ratingTV.text = rating
        holder.itemView.rootCV.setOnClickListener { v ->
            val intent = Intent()
            val bundle = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity, holder.itemView.thumbnailIV, holder.itemView.thumbnailIV.transitionName).toBundle()
            intent.setClass(mContext, AboutMovieActivity::class.java)
            intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mSearchedMovies[position].id)
            intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mSearchedMovies[position].posterPath)
            intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mSearchedMovies[position].title)
            mContext.startActivity(intent, bundle)
        }
    }

    override fun getItemCount(): Int {
        return mSearchedMovies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

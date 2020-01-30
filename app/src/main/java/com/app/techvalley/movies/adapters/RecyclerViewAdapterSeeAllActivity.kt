package com.app.techvalley.movies.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.techvalley.movies.AboutMovieActivity
import com.app.techvalley.movies.utils.IntentConstants
import com.app.techvalley.movies.models.Movie
import com.app.techvalley.movies.R
import com.app.techvalley.movies.network.URLConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.see_all_recyclerview_item.view.*

import java.util.ArrayList


/**
 * Created by KeshavAggarwal on 31/01/17.
 */


class RecyclerViewAdapterSeeAllActivity(private val mMovies: ArrayList<Movie>?, internal var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapterSeeAllActivity.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.see_all_recyclerview_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mMovies != null) {
            holder.itemView.nameTV.text = mMovies[position].title
            Picasso.get().load(URLConstants.IMAGE_BASE_URL + mMovies[position].posterPath).into(holder.itemView.thumbnailIV)
            if (mMovies[position].date.length >= 5) {
                val date = mMovies[position].date.substring(0, 4)
                holder.itemView.releaseDateTV.text = date
            }
            val rating = java.lang.Double.toString(mMovies[position].rating)
            holder.itemView.ratingTV.text = rating
            holder.itemView.rootCV.setOnClickListener { v ->
                val intent = Intent()
                val bundle = ActivityOptions.makeSceneTransitionAnimation(mContext as Activity, holder.itemView.thumbnailIV, holder.itemView.thumbnailIV.transitionName).toBundle()
                intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_ID, mMovies[position].id)
                intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mMovies[position].posterPath)
                intent.putExtra(IntentConstants.INTENT_KEY_MOVIE_NAME, mMovies[position].title)
                intent.setClass(mContext, AboutMovieActivity::class.java)
                mContext.startActivity(intent, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return mMovies!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}



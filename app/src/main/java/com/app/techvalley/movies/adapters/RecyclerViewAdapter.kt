package com.app.techvalley.movies.adapters

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.app.techvalley.movies.models.Movie
import com.app.techvalley.movies.OnRecyclerViewItemClickListener
import com.app.techvalley.movies.R
import com.app.techvalley.movies.network.URLConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizontal_cardview_movies.view.*

import java.util.ArrayList

/**
 * Created by KeshavAggarwal on 06/01/17.
 */

class RecyclerViewAdapter(private val mMovies: ArrayList<Movie>?, internal var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var listener: OnRecyclerViewItemClickListener? = null
    private var verticalPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_cardview_movies, parent, false)
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
            holder.itemView.setOnClickListener { v ->
                if (listener != null)
                    listener!!.onRecyclerViewItemClicked(verticalPosition, position, holder.itemView.thumbnailIV)
            }
        }
    }


    override fun getItemCount(): Int {
        return mMovies?.size ?: 0
    }


    fun setOnItemClickListener(listener: OnRecyclerViewItemClickListener, verticalPosition: Int) {
        this.listener = listener
        this.verticalPosition = verticalPosition
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}

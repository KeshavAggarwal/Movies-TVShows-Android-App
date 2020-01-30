package com.app.techvalley.movies.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.techvalley.movies.models.Cast
import com.app.techvalley.movies.R
import com.app.techvalley.movies.network.URLConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_cast_list_item_view.view.*

import java.util.ArrayList

/**
 * Created by KeshavAggarwal on 10/02/17.
 */

class RecyclerViewAdapterMovieCast(private val mMovieCast: ArrayList<Cast>?, internal var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapterMovieCast.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_cast_list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mMovieCast != null) {
            holder.itemView.nameTV.text = mMovieCast[position].name
            Picasso.get().load(URLConstants.IMAGE_BASE_URL + mMovieCast[position].profile_path).into(holder.itemView.movieCastIV)
            val character = "as " + mMovieCast[position].character
            holder.itemView.castCharacterTV.text = character
        }

    }

    override fun getItemCount(): Int {
        return mMovieCast!!.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}

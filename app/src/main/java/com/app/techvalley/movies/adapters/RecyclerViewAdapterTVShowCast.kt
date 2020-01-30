package com.app.techvalley.movies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.techvalley.movies.R
import com.app.techvalley.movies.models.Cast
import com.app.techvalley.movies.network.URLConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tvshow_cast_list_item_view.view.*
import java.util.*

/**
 * Created by KeshavAggarwal on 13/03/17.
 */

class RecyclerViewAdapterTVShowCast(private val mTvShowCast: ArrayList<Cast>?, internal var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapterTVShowCast.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tvshow_cast_list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mTvShowCast != null) {
            holder.itemView.castNameTV.text = mTvShowCast[position].name
            Picasso.get().load(URLConstants.IMAGE_BASE_URL + mTvShowCast[position].profile_path).into(holder.itemView.castIV)
            val character = "as " + mTvShowCast[position].character
            holder.itemView.castCharacterTV.text = character
        }
    }

    override fun getItemCount(): Int {
        return mTvShowCast?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

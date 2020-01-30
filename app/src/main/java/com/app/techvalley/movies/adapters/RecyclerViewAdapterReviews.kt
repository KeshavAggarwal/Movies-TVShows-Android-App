package com.app.techvalley.movies.adapters

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.app.techvalley.movies.R
import com.app.techvalley.movies.models.Review
import kotlinx.android.synthetic.main.review_list_item_view.view.*

import java.util.ArrayList

/**
 * Created by KeshavAggarwal on 14/03/17.
 */

class RecyclerViewAdapterReviews(private val mReviews: ArrayList<Review>, internal var mContext: Context) : RecyclerView.Adapter<RecyclerViewAdapterReviews.ViewHolder>() {
    private var isExpanded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.review_list_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var secondInitialOfAuthor: String? = null
        for (i in 0 until mReviews[position].author.length) {
            if (mReviews[position].author[i] == ' ') {
                secondInitialOfAuthor = mReviews[position].author.substring(i + 1, i + 2)
                holder.itemView.circleTV.text = mReviews[position].author.substring(0, 1) + secondInitialOfAuthor
                break
            } else
                holder.itemView.circleTV.text = mReviews[position].author.substring(0, 1)
        }
        holder.itemView.reviewHeadingTV.text = "A review by " + mReviews[position].author
        holder.itemView.reviewSubheadingTV.text = "By " + mReviews[position].author
        holder.itemView.reviewsTextView.text = mReviews[position].content

        holder.itemView.reviewsTextView.post {
            if (holder.itemView.reviewsTextView.lineCount == 9) {
                holder.itemView.readMoreTV.visibility = View.VISIBLE
                holder.itemView.readMoreTV.text = "Read More"
                holder.itemView.readMoreTV.setOnClickListener {
                    if (isExpanded) {
                        holder.itemView.reviewsTextView.maxLines = 9
                        holder.itemView.reviewsTextView.ellipsize = TextUtils.TruncateAt.END
                        isExpanded = false
                        holder.itemView.readMoreTV.text = "read more"
                    } else {
                        holder.itemView.reviewsTextView.maxLines = Integer.MAX_VALUE
                        holder.itemView.reviewsTextView.ellipsize = null
                        isExpanded = true
                        holder.itemView.readMoreTV.text = "hide"

                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return mReviews.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}



package com.renatoramos.tajawal.presentation.ui.hotel.list.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.renatoramos.tajawal.R

class HotelListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    val placeGuideImageView by lazy { itemView?.findViewById<ImageView>(R.id.placeGuideImageView)}
    val titleTextView by lazy { itemView?.findViewById<TextView>(R.id.titleTextView)}
}
package com.renatoramos.tajawal.presentation.ui.hotel.list.adapters

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.renatoramos.tajawal.R
import com.renatoramos.tajawal.common.extensions.inflate
import com.renatoramos.tajawal.common.extensions.loadWithGlide
import com.renatoramos.tajawal.common.ui.components.DrawableRequestListener
import com.renatoramos.tajawal.data.model.HotelModel
import kotlinx.android.synthetic.main.hotel_viewholder.view.*

class HotelListRecyclerAdapter (private val context: Context, private val hotelModelList: List<HotelModel>, private val hotelListAdapterListener: HotelListAdapterListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mainViewHolder = HotelListViewHolder(parent.inflate(R.layout.hotel_viewholder))

        //Cell clicks
        mainViewHolder.itemView.setOnClickListener {hotelListAdapterListener.onItemClick(mainViewHolder.adapterPosition)}
        return mainViewHolder
    }

    override fun getItemCount(): Int {
        return hotelModelList?.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        // Just bind View Objects.
        val mainViewHolder = viewHolder as HotelListViewHolder
        val hotelModel = hotelModelList[position]

        hotelModel.image!![0].url?.let { setupImageView(mainViewHolder, it) }
        mainViewHolder.titleTextView?.text = hotelModel.summary!!.hotelName
    }

    private fun setupImageView(holder: HotelListViewHolder, url: String) {
        holder.itemView.placeGuideImageView.loadWithGlide(url, object : DrawableRequestListener() {
            override fun onResourceReady(bitmap: Bitmap){
                holder.itemView.placeGuideImageView.setRatio(bitmap.width.toFloat() / bitmap.height)
            }
        })
    }
}

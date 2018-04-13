package com.renatoramos.tajawal.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HotelsModel(
    @SerializedName("hotel")
    @Expose var hotel: List<HotelModel>?)

package com.renatoramos.tajawal.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HotelModel(
    @SerializedName("hotelId")
    @Expose
    var hotelId: Int?,
    @SerializedName("image")
    @Expose
    var image: List<ImageModel>?,
    @SerializedName("location")
    @Expose
    var location: LocationModel?,
    @SerializedName("summary")
    @Expose
    var summary: SummaryModel?)

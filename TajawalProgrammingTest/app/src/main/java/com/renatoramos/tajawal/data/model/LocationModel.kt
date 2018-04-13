package com.renatoramos.tajawal.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LocationModel(
    @SerializedName("address")
    @Expose
    var address: String?,
    @SerializedName("latitude")
    @Expose
    var latitude: Double?,
    @SerializedName("longitude")
    @Expose
    var longitude: Double? )
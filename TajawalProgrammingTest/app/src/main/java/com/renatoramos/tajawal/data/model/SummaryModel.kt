package com.renatoramos.tajawal.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SummaryModel(
    @SerializedName("highRate")
    @Expose
    var highRate: Double?,
    @SerializedName("hotelName")
    @Expose
    var hotelName: String?,
    @SerializedName("lowRate")
    @Expose
    var lowRate: Double? )
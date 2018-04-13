package com.renatoramos.tajawal.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("url")
    @Expose
    var url: String?)
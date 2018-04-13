package com.renatoramos.tajawal.common.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.renatoramos.tajawal.common.ui.components.DrawableRequestListener



fun ImageView.loadWithGlide(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadWithGlide(url: String, listener: DrawableRequestListener) {
    Glide.with(context).load(url).listener(listener).into(this)
}

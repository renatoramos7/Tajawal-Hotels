package com.renatoramos.tajawal.common.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


abstract class DrawableRequestListener : RequestListener<Drawable> {

    abstract fun onResourceReady(bitmap: Bitmap)

        override fun onLoadFailed(e: GlideException?, model: Any,
                                  target: Target<Drawable>, isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>,
                                     dataSource: DataSource, isFirstResource: Boolean
        ): Boolean {
            onResourceReady(getBitmap(resource))
            return false
        }

        private fun getBitmap(drawable: Drawable): Bitmap {
            return when (drawable) {
                is BitmapDrawable -> drawable.bitmap
                else -> (drawable as GifDrawable).firstFrame
            }
        }
    }
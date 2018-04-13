package com.renatoramos.tajawal.presentation.ui.hotel.imageviewer

interface ImageViewerContract {

    interface View {

        fun loadImage(url: String)

        fun addImageClick()
    }

    interface Presenter {

        fun attachUrl(url: String)
    }
}
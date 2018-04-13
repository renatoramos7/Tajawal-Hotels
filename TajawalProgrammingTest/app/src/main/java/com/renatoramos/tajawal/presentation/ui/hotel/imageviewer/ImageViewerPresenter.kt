package com.renatoramos.tajawal.presentation.ui.hotel.imageviewer

import com.renatoramos.tajawal.presentation.base.BasePresenter
import javax.inject.Inject

class ImageViewerPresenter @Inject constructor(view: ImageViewerContract.View)
    : BasePresenter<ImageViewerContract.View>(view), ImageViewerContract.Presenter {

    override fun onStart() {
        view.addImageClick()
    }

    override fun attachUrl(url: String) {
        view.loadImage(url)
    }

}

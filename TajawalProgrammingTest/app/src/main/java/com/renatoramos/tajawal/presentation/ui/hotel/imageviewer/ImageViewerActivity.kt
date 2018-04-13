package com.renatoramos.tajawal.presentation.ui.hotel.imageviewer

import android.os.Bundle
import com.renatoramos.tajawal.R
import com.renatoramos.tajawal.common.constants.AppConstants
import com.renatoramos.tajawal.common.extensions.loadWithGlide
import com.renatoramos.tajawal.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image_viewer.*
import javax.inject.Inject

class ImageViewerActivity : BaseActivity(), ImageViewerContract.View {

    @Inject
    lateinit var presenter: ImageViewerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        initialize()
    }

    override fun loadImage(url: String) {
        imageViewTouch?.loadWithGlide(url)
    }

    override fun addImageClick() {
        imageViewTouch?.setSingleTapListener(this::finish)
    }

    private fun initialize() {
        presenter.attachUrl(intent.getStringExtra(AppConstants.IMAGE_URL))
        presenter.onStart()
    }

}

package com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.module

import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerActivity
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerContract
import dagger.Binds
import dagger.Module

@Module
abstract class ImageViewerActivityModule {

    @Binds
    abstract fun provideImageViewer(imageViewerActivity: ImageViewerActivity): ImageViewerContract.View
}
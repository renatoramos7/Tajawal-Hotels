package com.renatoramos.tajawal.presentation.ui.hotel.detail.module

import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsActivity
import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsContract
import dagger.Binds
import dagger.Module

@Module
abstract class DetailsActivityModule {

    @Binds
    abstract fun provideDetailsView(detailsActivity: DetailsActivity): DetailsContract.View

}
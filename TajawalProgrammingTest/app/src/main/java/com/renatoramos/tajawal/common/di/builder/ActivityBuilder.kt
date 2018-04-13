package com.renatoramos.tajawal.common.di.builder

import com.renatoramos.tajawal.common.di.scope.ActivityScope
import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsActivity
import com.renatoramos.tajawal.presentation.ui.hotel.detail.module.DetailsActivityModule
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerActivity
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.module.ImageViewerActivityModule
import com.renatoramos.tajawal.presentation.ui.hotel.list.HotelListActivity
import com.renatoramos.tajawal.presentation.ui.hotel.list.module.HotelListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(HotelListActivityModule::class)])
    internal abstract fun bindHotelListActivityModule(): HotelListActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(DetailsActivityModule::class)])
    internal abstract fun bindDetailsActivityModule(): DetailsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ImageViewerActivityModule::class)])
    internal abstract fun bindImageViewerActivity(): ImageViewerActivity
}

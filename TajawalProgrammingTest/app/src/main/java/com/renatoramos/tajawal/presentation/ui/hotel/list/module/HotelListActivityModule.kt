package com.renatoramos.tajawal.presentation.ui.hotel.list.module

import com.renatoramos.tajawal.presentation.ui.hotel.list.HotelListActivity
import com.renatoramos.tajawal.presentation.ui.hotel.list.HotelListContract
import dagger.Binds
import dagger.Module

@Module
abstract class HotelListActivityModule {

    @Binds
    internal abstract fun provideHotelListView(usersActivity: HotelListActivity): HotelListContract.View
}

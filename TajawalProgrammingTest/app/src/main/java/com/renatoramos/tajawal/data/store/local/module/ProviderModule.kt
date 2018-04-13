package com.renatoramos.tajawal.data.store.local.module

import com.renatoramos.tajawal.data.store.local.HotelProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ProviderModule {

    @Reusable
    @Provides
    internal fun provideHotelProvider(): HotelProvider {
        return HotelProvider()
    }

}
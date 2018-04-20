package com.renatoramos.tajawal.common.di.module

import com.renatoramos.tajawal.data.store.HotelsRepository
import com.renatoramos.tajawal.data.store.local.HotelProvider
import com.renatoramos.tajawal.data.store.remote.network.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule{

    @Provides
    @Singleton
    fun providesHotelsRepository(networkService: NetworkService, hotelProvider: HotelProvider): HotelsRepository {
        return HotelsRepository(networkService, hotelProvider)
    }

}
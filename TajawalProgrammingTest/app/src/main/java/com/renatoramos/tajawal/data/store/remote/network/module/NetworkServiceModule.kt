package com.renatoramos.tajawal.data.store.remote.network.module

import com.renatoramos.tajawal.data.store.remote.network.NetworkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkServiceModule {

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}
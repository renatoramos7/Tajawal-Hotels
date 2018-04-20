package com.renatoramos.tajawal.common.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class SettingsModule {

    @Provides
    @Singleton
    @Named(BASE_URL)
    internal fun provideServerUrl(): String {
        return "http://gchbib.de/tajawal/"
    }

    companion object {
        const val BASE_URL = "Settings.ServerUrl"
    }
}
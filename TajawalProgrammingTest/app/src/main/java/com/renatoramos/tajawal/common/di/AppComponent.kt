package com.renatoramos.tajawal.common.di

import com.renatoramos.tajawal.MainApplication
import com.renatoramos.tajawal.common.di.builder.ActivityBuilder
import com.renatoramos.tajawal.common.di.module.ApplicationModule
import com.renatoramos.tajawal.common.di.module.NetworkModule
import com.renatoramos.tajawal.common.di.module.RepositoryModule
import com.renatoramos.tajawal.common.di.module.SettingsModule
import com.renatoramos.tajawal.data.store.local.module.ProviderModule
import com.renatoramos.tajawal.data.store.remote.network.module.NetworkServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (ApplicationModule::class),
    (ActivityBuilder::class),
    (SettingsModule::class),
    (NetworkModule::class),
    (NetworkServiceModule::class),
    (ProviderModule::class),
    (RepositoryModule::class)
])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(mainApplication: MainApplication): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun build(): AppComponent
    }
}
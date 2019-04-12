package com.renatoramos.tajawal

import android.app.Activity
import android.app.Application
import com.renatoramos.tajawal.common.di.DaggerAppComponent
import com.renatoramos.tajawal.common.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.paperdb.Paper
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        init()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }


    private fun init() {
        initDagger()
        initPaperDb()
    }
    private fun initDagger() {

        DaggerAppComponent
                .builder()
                .application(this)
                .applicationModule(ApplicationModule(this))
                .build()
                .inject(this)
    }


    private fun initPaperDb(){
        Paper.init(this)
    }

}
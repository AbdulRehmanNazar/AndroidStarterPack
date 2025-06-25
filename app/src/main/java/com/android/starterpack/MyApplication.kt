package com.android.starterpack

import android.app.Application
import com.android.starterpack.di.appModule
import com.android.starterpack.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, networkModule)
        }
    }
}
package com.dongnh.permissionsummary.app

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dongnh.permissionsummary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this@MyApp)
        setupTimber()
        setupKoin()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }

}
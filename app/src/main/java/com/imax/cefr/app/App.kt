package com.imax.cefr.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.imax.cefr.di.dataModule
import com.imax.cefr.di.domainModule
import com.imax.cefr.di.localStorageModule
import com.imax.cefr.di.networkModule
import com.imax.cefr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(viewModelModule, dataModule, domainModule, networkModule, localStorageModule))
        }
    }
}

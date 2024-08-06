package com.example.cefr.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.cefr.di.parentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            //ozgerttim
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(parentModule)
        }
    }
}

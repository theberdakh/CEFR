package com.example.cefr.di

import android.content.Context
import android.content.SharedPreferences
import com.example.cefr.utils.LocalStorage
import org.koin.dsl.module

val localStorageModule = module {
    single<LocalStorage> {
        val pref = getSharedPreference(context = get())
        LocalStorage(preference = pref)
    }

}

fun getSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences("localstorage", Context.MODE_PRIVATE)
}
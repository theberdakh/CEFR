package com.example.cefr.di

import com.example.cefr.di.appModule
import com.example.cefr.di.dataModule
import com.example.cefr.di.domainModule
import com.example.cefr.di.localStorageModule
import com.example.cefr.di.networkModule
import org.koin.dsl.module

val parentModule = module {
    includes(appModule, dataModule, domainModule, networkModule, localStorageModule)
}
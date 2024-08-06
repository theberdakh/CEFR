package com.imax.cefr.di

import org.koin.dsl.module

val parentModule = module {
    includes(appModule, dataModule, domainModule, networkModule, localStorageModule)
}

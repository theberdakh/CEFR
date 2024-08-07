package com.imax.cefr.di

import com.imax.cefr.data.repository.login.impl.LoginRepositoryImpl
import com.imax.cefr.data.repository.login.LoginRepository
import org.koin.dsl.module

val dataModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(api = get())
    }
}

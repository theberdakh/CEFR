package com.imax.cefr.di

import com.imax.cefr.data.repository.login.impl.LoginRepositoryImpl
import com.imax.cefr.data.repository.login.LoginRepository
import com.imax.cefr.data.repository.stream.StreamRepository
import com.imax.cefr.data.repository.stream.StreamRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(api = get())
    }
    single<StreamRepository> {
        StreamRepositoryImpl(api = get())
    }
}

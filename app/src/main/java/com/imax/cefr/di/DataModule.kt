package com.imax.cefr.di

import com.imax.cefr.data.repository.LoginRepositoryImpl
import com.imax.cefr.domain.repository.LoginRepository
import org.koin.dsl.module

val dataModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(api = get())
    }
}

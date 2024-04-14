package com.example.cefr.di

import com.example.cefr.data.repository.LoginRepositoryImpl
import com.example.cefr.domain.repository.LoginRepository
import org.koin.dsl.module

val dataModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(api = get())
    }
}
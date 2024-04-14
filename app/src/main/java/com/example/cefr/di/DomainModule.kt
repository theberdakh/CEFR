package com.example.cefr.di


import com.example.cefr.domain.useCase.LoginUseCase
import com.example.cefr.domain.useCase.impl.LoginUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCaseImpl(repo = get())
    }
}
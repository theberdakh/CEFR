package com.imax.cefr.di


import com.imax.cefr.domain.useCase.LoginUseCase
import com.imax.cefr.domain.useCase.impl.LoginUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCaseImpl(repo = get())
    }
}

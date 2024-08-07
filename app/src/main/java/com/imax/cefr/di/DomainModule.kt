package com.imax.cefr.di


import com.imax.cefr.domain.use_case.LoginUseCase
import com.imax.cefr.domain.use_case.impl.LoginUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCaseImpl(repo = get())
    }
}

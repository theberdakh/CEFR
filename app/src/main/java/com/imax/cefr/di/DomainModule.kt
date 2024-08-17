package com.imax.cefr.di


import com.imax.cefr.domain.use_case.LoginUseCase
import com.imax.cefr.domain.use_case.StreamUseCase
import com.imax.cefr.domain.use_case.impl.LoginUseCaseImpl
import com.imax.cefr.domain.use_case.impl.StreamUseCaseImpl
import com.imax.cefr.domain.use_case.TwitchUseCase
import com.imax.cefr.domain.use_case.impl.TwitchUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    single<LoginUseCase> {
        LoginUseCaseImpl(repo = get())
    }

    single<StreamUseCase> {
        StreamUseCaseImpl(repo = get())
    }

    single<TwitchUseCase> {
        TwitchUseCaseImpl(repository = get())
    }
}

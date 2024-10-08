package com.imax.cefr.di

import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.presentation.TwitchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(useCase = get())
    }
    viewModel<StreamViewModel> {
        StreamViewModel(useCase = get())
    }

    viewModel<TwitchViewModel> {
        TwitchViewModel(usecase = get())
    }

}

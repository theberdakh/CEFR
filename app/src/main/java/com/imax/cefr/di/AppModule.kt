package com.imax.cefr.di

import com.imax.cefr.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(useCase = get())
    }
}

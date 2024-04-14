package com.example.cefr.di

import com.example.cefr.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(useCase = get())
    }
}
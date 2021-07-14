package com.example.notegame.di

import com.example.core.domain.usecase.GamesInteractor
import com.example.core.domain.usecase.IGamesUseCase
import com.example.notegame.ui.detail.DetailViewModel
import com.example.notegame.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IGamesUseCase> {
        GamesInteractor(get())
    }
}

val viewModuleModule = module {
    factory {
        Dispatchers.IO
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        DetailViewModel(get(), get())
    }
}
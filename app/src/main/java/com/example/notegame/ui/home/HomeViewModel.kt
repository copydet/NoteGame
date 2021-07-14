package com.example.notegame.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.core.domain.usecase.IGamesUseCase

class HomeViewModel(iGamesUseCase: IGamesUseCase) : ViewModel() {
    val games = iGamesUseCase.getAllGames().cachedIn(viewModelScope)
}
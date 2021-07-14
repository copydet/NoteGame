package com.example.notegame.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Games
import com.example.core.domain.usecase.IGamesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gamesUseCase: IGamesUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _detail = MutableLiveData<Games>()
    val detail: LiveData<Games> get() = _detail

    fun getDetail(id: Int){
        viewModelScope.launch {
            gamesUseCase.getGames(id)
                .collect {
                    _detail.postValue(it)
                }
        }
    }
}
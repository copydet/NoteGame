package com.example.core.domain.repository

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.domain.model.Games
import com.example.core.domain.model.SavedGames
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {
    fun getAllGames(): Flow<PagingData<Games>>
    fun getSearchGames(search: String): Flow<PagingData<Games>>
    @Suppress("DEPRECATION")
    fun getListGamesSaved(): Flow<PagedList<Games>>
    fun getSavedListGames(savedId: Int): Flow<SavedGames?>
    suspend fun insertSaved(savedId: Int)
    suspend fun deleteSaved(savedId: Int)

    suspend fun getGames(id: Int): Flow<Games>
    suspend fun getGamesById(id: Int): Flow<Games>
}
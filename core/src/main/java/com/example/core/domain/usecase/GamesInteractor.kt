package com.example.core.domain.usecase

import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.core.domain.model.Games
import com.example.core.domain.model.SavedGames
import com.example.core.domain.model.ScreenShots
import com.example.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor (private val iGamesRepository: IGamesRepository): IGamesUseCase {
    override fun getAllGames(): Flow<PagingData<Games>> =
        iGamesRepository.getAllGames()

    override fun getSearchGames(search: String): Flow<PagingData<Games>> =
        iGamesRepository.getSearchGames(search)

    override fun getSavedListGames(savedId: Int): Flow<SavedGames?> =
        iGamesRepository.getSavedListGames(savedId)

    @Suppress("DEPRECATION")
    override fun getListGamesSaved(): Flow<PagedList<Games>> =
        iGamesRepository.getListGamesSaved()

    override suspend fun insertSaved(savedId: Int) =
        iGamesRepository.insertSaved(savedId)

    override suspend fun deleteSaved(savedId: Int) =
        iGamesRepository.deleteSaved(savedId)

    override suspend fun getGames(id: Int): Flow<Games> =
        iGamesRepository.getGames(id)

    override suspend fun getGamesById(id: Int) : Flow<Games> = iGamesRepository.getGamesById(id)

    override suspend fun getScreenShotsGame(id: Int) : Flow<List<ScreenShots>> =
        iGamesRepository.getScreenShotsGame(id)
}
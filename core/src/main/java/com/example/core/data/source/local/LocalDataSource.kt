package com.example.core.data.source.local

import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.RemotePageKeysEntity
import com.example.core.data.source.local.entity.SavedGamesEntity
import com.example.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val gamesDao: GamesDao) {

    //Get List All Games
    fun getAllGames(): PagingSource<Int, GamesEntity> = gamesDao.getGames()

    fun getSearchGames(search: String): PagingSource<Int, GamesEntity> =
        gamesDao.getSearchGames("$search%")

    suspend fun insertGames(games: List<GamesEntity>) = gamesDao.insertGames(games)

    suspend fun deleteAllGames() = gamesDao.deleteAllGames()

    // Get Detail Games
    fun getDetailGames(id: Int) = gamesDao.getDetailGames(id)

    suspend fun updateDetailGames(games: GamesEntity) = gamesDao.updateDetailGames(games)

    //Get Remote Keys
    suspend fun insertRemoteKeysGameAll(remoteKey: List<RemotePageKeysEntity>) =
        gamesDao.insertAll(remoteKey)

    suspend fun remoteKeysGamesId(gamesId: Int): RemotePageKeysEntity? = gamesDao.remoteKeyId(gamesId)

    suspend fun clearRemoteKeys() = gamesDao.clearRemoteKeys()

    //Get Saved Games
    fun getSavedList(id: Int): Flow<SavedGamesEntity> = gamesDao.getSavedList(id)

    fun getListSavedGames(): DataSource.Factory<Int, GamesEntity> = gamesDao.getListSavedGames()

    suspend fun insertSaved(savedGames: SavedGamesEntity) = gamesDao.insertSaved(savedGames)

    suspend fun deleteSaved(savedGames: SavedGamesEntity) = gamesDao.deleteSaved(savedGames)
}
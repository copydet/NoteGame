package com.example.core.data.source.local.room

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.RemotePageKeysEntity
import com.example.core.data.source.local.entity.SavedGamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    // Get List Of Games
    @Query("SELECT * FROM games order by added DESC")
    fun getGames(): PagingSource<Int, GamesEntity>

    @Query("select * from games where name like :search")
    fun getSearchGames(search: String): PagingSource<Int, GamesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GamesEntity>)

    @Query("select * from games where id = :id")
    fun getDetailGames(id: Int): Flow<GamesEntity>

    @Update
    suspend fun updateDetailGames(games: GamesEntity)

    //Next Page And Previous Page / Mengambil Next Data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemotePageKeysEntity>)

    @Query("SELECT * FROM remoteKeys where id =:id")
    suspend fun remoteKeyId(id: Int): RemotePageKeysEntity?

    @Query("DELETE FROM remoteKeys")
    suspend fun clearRemoteKeys()

    // Get Saved of Games from Detail
    @Query("select * from games a join savedGamesEntity b on a.id = b.savedId")
    fun getListSavedGames(): DataSource.Factory<Int, GamesEntity>

    @Query("select * from savedGamesEntity where savedId = :id")
    fun getSavedList(id: Int): Flow<SavedGamesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaved(saved: SavedGamesEntity)

    @Delete
    suspend fun deleteSaved(saved: SavedGamesEntity)

    @Query("delete from games")
    suspend fun deleteAllGames()
}
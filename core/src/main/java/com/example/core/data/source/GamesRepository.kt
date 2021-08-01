package com.example.core.data.source

import androidx.lifecycle.asFlow
import androidx.paging.*
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.SavedGamesEntity
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.RemoteDataSourceDetail
import com.example.core.domain.model.Games
import com.example.core.domain.model.SavedGames
import com.example.core.domain.model.ScreenShots
import com.example.core.domain.repository.IGamesRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@ExperimentalPagingApi
class GamesRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val remoteDataSourceDetail: RemoteDataSourceDetail
): IGamesRepository {
    companion object {
        private const val pageSize = 20
    }

    override fun getAllGames(): Flow<PagingData<Games>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        remoteMediator = GamesMediator(localDataSource, remoteDataSource),
        pagingSourceFactory = {
            localDataSource.getAllGames()
        }
    ).flow.map {
        DataMapper.mapListGamesEntitiesToDomain(it)
    }


// start take experiment detail games
    override suspend fun getGames(id: Int): Flow<Games> =
        remoteDataSourceDetail.getDetailGames(id).map {
            DataMapper.responseToDomainGames(it)
        }

    override suspend fun getGamesById(id: Int): Flow<Games> =
        localDataSource.getDetailGames(id).map {
            DataMapper.mapEntityToDomainGames(it)
        }
// finish

    //take a ScreenshotItem

    override suspend fun getScreenShotsGame(id: Int): Flow<List<ScreenShots>> =
        remoteDataSourceDetail.getScreenShotsGame(id).map {
            DataMapper.responseToDomainScreenShots(it)
        }


    override fun getSearchGames(search: String): Flow<PagingData<Games>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        remoteMediator = GamesMediator(localDataSource, remoteDataSource, search),
        pagingSourceFactory = {
            localDataSource.getSearchGames(search)
        }
    ).flow.map {
        DataMapper.mapListGamesEntitiesToDomain(it)
    }

    @Suppress("DEPRECATION")
    override fun getListGamesSaved(): Flow<PagedList<Games>> = LivePagedListBuilder(
        localDataSource.getListSavedGames().map{ DataMapper.mapGamesEntitiesToDomain(it) },
        PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(20)
            .setPageSize(20).build()
    ).build().asFlow()

    override fun getSavedListGames(savedId: Int): Flow<SavedGames?> =
        localDataSource.getSavedList(savedId).map {
            DataMapper.mapSavedGamesEntitiesToDomain(it)
        }

    override suspend fun insertSaved(savedId: Int) =
        localDataSource.insertSaved(SavedGamesEntity(savedId))

    override suspend fun deleteSaved(savedId: Int) =
        localDataSource.deleteSaved(SavedGamesEntity(savedId))
}
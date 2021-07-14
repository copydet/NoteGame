package com.example.core.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.RemotePageKeysEntity
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.utils.DataMapper
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class GamesMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val searchQuery: String? = null
): RemoteMediator<Int, GamesEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GamesEntity>
    ): MediatorResult {
        val page = when (loadType){
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                //if the previous key is null, the the list is empty so we should wait for data
                // fetched by remote refresh and can simply skip loading this time by returning
                //`false` for endOfPaginationReached
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                //if the next key is null, the the list is empty so we should wait for data
                // fetched by remote refresh and can simply skip loading this time by returning
                // `false` for endOfPaginationReached
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                nextKey
            }
        }
        try {
            val remoteResponse = if (searchQuery == null){
                remoteDataSource.getGames(page, state.config.pageSize)
            }else{
                remoteDataSource.getSearchGames(searchQuery, page, state.config.pageSize)
            }
            val remoteResponseItem = remoteResponse.results
            val endPageReach = remoteResponseItem.isEmpty()
            if (loadType == LoadType.REFRESH){
                localDataSource.clearRemoteKeys()
                localDataSource.deleteAllGames()
            }
            val keys = remoteResponseItem.map {
                RemotePageKeysEntity(
                    id = it.id ?: 0,
                    prevKey = remoteResponse.previous?.substringAfter("page=")?.substringBefore("&")
                        ?.toIntOrNull(),
                    nextKey = remoteResponse.next?.substringAfter("page=")?.substringBefore("&")
                        ?.toInt()
                )
            }
            localDataSource.insertRemoteKeysGameAll(keys)
            localDataSource.insertGames(
                DataMapper.mapListGamesResponseToEntities(remoteResponseItem)
            )
            return MediatorResult.Success(endOfPaginationReached = endPageReach)
        }catch (exception: IOException){
            return MediatorResult.Error(exception)
        }catch (exception: HttpException){
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GamesEntity>): RemotePageKeysEntity?{
        //Get the First Page That Was Retrived, that Contained Items
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                //Get The remote keys of the first items retrieved
                localDataSource.remoteKeysGamesId(it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GamesEntity>): RemotePageKeysEntity?{
        //get the last page that was retrieved, that contained items.
        //from that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                //get the remote keys of the last item retrieved
                localDataSource.remoteKeysGamesId(it.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GamesEntity>): RemotePageKeysEntity? {
        //the paging library is trying to load data after the anchor position
        //get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { gamesId ->
                localDataSource.remoteKeysGamesId(gamesId)
            }
        }
    }
}
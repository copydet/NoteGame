package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.Resource
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.DetailResponseGames
import com.example.core.data.source.remote.response.ScreenShotsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSourceDetail(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val key: String
){
    suspend fun getDetailGames(id:Int): Flow<DetailResponseGames> {
        return flow {
            try {
                val data = apiService.getDetailGames(id, key)
                emit(data)
            }catch (e: Exception){
                Log.e("RemoteDataSourceDetail", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getScreenShotsGame(id: Int): Flow<List<ScreenShotsResponse>>{
        return flow {
            try {
                val response = apiService.getScreenshots(id = id, key)
                val data = response.results
                if (data?.isNotEmpty() ?: return@flow) emit(data)
            } catch (e:Exception){
                Log.e("RemoteDataSourceDetail", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
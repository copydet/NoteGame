package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseDataSource {
    suspend fun <T> getResult(
        call: suspend () -> Response<T>,
        dispatcher: CoroutineDispatcher
    ): Flow<ApiResponse<T>> {
        return flow {
            try {
                val responseCall = call()
                if (responseCall.isSuccessful) {
                    val responseBody = responseCall.body()
                    emit(ApiResponse.Success(responseBody!!))
                }else{
                    emit(ApiResponse.Error("${responseCall.code()} ${responseCall.message()}"))
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(dispatcher)
    }
}
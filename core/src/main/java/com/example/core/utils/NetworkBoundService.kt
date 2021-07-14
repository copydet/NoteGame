package com.example.core.utils

import com.example.core.data.Resource
import com.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

fun <ResultType, RequestType> networkBoundResource(
    loadFromDB: () -> Flow<ResultType>,
    createCall: suspend () -> Flow<ApiResponse<RequestType>>,
    saveCallResult: suspend (RequestType) -> Unit
): Flow<Resource<ResultType>> {
    return flow {
        emit(Resource.Loading())
        when (val responseStatus = createCall().first()){
            is ApiResponse.Success ->{
                responseStatus.data?.let { saveCallResult(it) }
                emitAll(loadFromDB().map {
                    Resource.Success(it)
                })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(responseStatus.errorMessage))
                val dataSource = loadFromDB().first()
                if (dataSource != null){
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
            }
        }
    }
}
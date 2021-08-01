package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.BaseResponseGames
import com.example.core.data.source.remote.response.BaseScreenshotsResponse
import com.example.core.data.source.remote.response.DetailResponseGames
import com.example.core.data.source.remote.response.ScreenShotsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games/lists/main")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("discover") discover: Boolean,
        @Query("ordering") ordering: String,
        @Query("page") page: Int,
        @Query("page_size") page_size: Int,
    ): BaseResponseGames

    @GET("games/{id}")
    suspend fun getDetailGames(
        @Path("id") id: Int,
        @Query("key") key: String,
    ): DetailResponseGames

    @GET("games/{id}/screenshots")
    suspend fun getScreenshots(
        @Path("id") id: Int,
        @Query("key") key: String
    ):BaseScreenshotsResponse<ScreenShotsResponse>

    @GET("games")
    suspend fun getSearchGames(
        @Query("key") key: String,
        @Query("search") search: String,
        @Query("page") page: Int,
        @Query("page_size") page_size: Int,
    ): BaseResponseGames
}
package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponseGames(

    @field:SerializedName("results")
    val results: List<DetailResponseGames>,

    @field:SerializedName("next")
    val next: String?,

    @field:SerializedName("previous")
    val previous: String?,
)
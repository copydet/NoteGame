package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScreenShotsResponse(

    @SerializedName("image")
    val image: String,

    @SerializedName("id")
    val id: Int?,
)
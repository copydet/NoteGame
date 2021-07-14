package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Platform(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("platform")
    val platform: Int,
)
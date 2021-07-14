package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StoresItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("store")
    val store: Store,

    @field:SerializedName("url")
    val url: String
)
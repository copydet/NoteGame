package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ParentPlatformsItem(

    @field:SerializedName("platform")
    val platform: Platform
)
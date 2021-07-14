package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotsItem>? = null,

    @field:SerializedName("added")
    val added: Int
)
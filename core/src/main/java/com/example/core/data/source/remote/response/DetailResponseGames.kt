package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponseGames(

    @field:SerializedName("developers")
    val developers: List<DevelopersItem>,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("playtime")
    val playtime: Int,

    @field:SerializedName("publishers")
    val publishers: List<PublishersItem>,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>,

    @field:SerializedName("released")
    val released: String,

    @field:SerializedName("description_raw")
    val description: String,

    @field:SerializedName("tags")
    val tags: List<TagsItem>,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("updated")
    val updated: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("stores")
    val stores: List<StoresItem>,

    @field:SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotsItem>? = null,

    @field:SerializedName("added")
    val added: Int
)
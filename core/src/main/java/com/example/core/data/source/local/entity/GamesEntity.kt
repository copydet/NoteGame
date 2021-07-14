package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.source.remote.response.*

@Entity(tableName = "games")
data class GamesEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "release")
    val release: String?,

    @ColumnInfo(name = "update")
    val update: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "playtime")
    val playtime: Int?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "website")
    val website: String?,



    @ColumnInfo(name = "added")
    val added: Int?
)
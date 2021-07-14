package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys")
data class RemotePageKeysEntity(
    @PrimaryKey
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

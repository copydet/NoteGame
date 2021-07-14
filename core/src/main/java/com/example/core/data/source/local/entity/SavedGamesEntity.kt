package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedGamesEntity")
data class SavedGamesEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "savedId")
    var id: Int,
)
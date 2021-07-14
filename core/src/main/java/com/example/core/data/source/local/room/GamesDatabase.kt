package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.source.local.entity.GamesEntity
import com.example.core.data.source.local.entity.RemotePageKeysEntity
import com.example.core.data.source.local.entity.SavedGamesEntity
import com.example.core.utils.Converter

@Database(entities = [GamesEntity::class, SavedGamesEntity::class, RemotePageKeysEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}
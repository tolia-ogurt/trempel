package com.trempel.core_network.favorites_db.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoritesEntity::class],
    version = 1
)
abstract class FavoritesDataBase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}
package com.example.trempel.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecentlyViewed::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getRecentlyViewedDao(): RecentlyViewedDao

}
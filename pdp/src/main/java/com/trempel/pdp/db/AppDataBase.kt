package com.trempel.pdp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecentlyViewed::class],
    version = 1
)
internal abstract class AppDataBase : RoomDatabase() {

    abstract fun getRecentlyViewedDao(): RecentlyViewedDao

}
package com.trempel.core_network.favorites_db.di

import android.content.Context
import androidx.room.Room
import com.trempel.core_network.bag_db.db.AppDataBase
import com.trempel.core_network.favorites_db.db.FavoritesDataBase
import dagger.Module
import dagger.Provides

@Module
class FavoritesDbModule {

    @Provides
    fun provideRoomFavoritesDataBase(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, TABLE_NAME_DB
    ).build()

    @Provides
    fun provideFavoritesDao(db: FavoritesDataBase) = db.getFavoritesDao()

    companion object {
        const val TABLE_NAME_DB = "favorites"
    }
}
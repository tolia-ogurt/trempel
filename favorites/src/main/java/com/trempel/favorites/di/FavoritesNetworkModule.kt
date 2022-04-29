package com.trempel.favorites.di

import android.content.Context
import androidx.room.Room
import com.trempel.core_network.favorites_db.db.FavoritesDataBase
import com.trempel.favorites.service.FavoritesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FavoritesNetworkModule {

    @Provides
    fun provideRoomFavoritesDataBase(context: Context) = Room.databaseBuilder(
        context,
        FavoritesDataBase::class.java, TABLE_NAME_DB
    ).build()

    @Provides
    fun provideFavoritesDao(db: FavoritesDataBase) = db.getFavoritesDao()

    @Provides
    internal fun provideFavoritesService(retrofit: Retrofit): FavoritesService {
        return retrofit.create(FavoritesService::class.java)
    }

    companion object {
        const val TABLE_NAME_DB = "favorites"
    }
}

package com.trempel.favorites.di

import com.trempel.core_network.favorites_db.db.FavoritesDbRepository
import com.trempel.core_network.favorites_db.db.FavoritesDbRepositoryImpl
import com.trempel.favorites.repository.FavoritesNetworkRepository
import com.trempel.favorites.repository.FavoritesNetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [FavoritesNetworkModule::class])
abstract class FavoritesDataModule {

    @Binds
    internal abstract fun provideFavoritesNetworkRepository(
        favoritesNetworkRepositoryImpl: FavoritesNetworkRepositoryImpl
    ): FavoritesNetworkRepository

    @Binds
    abstract fun provideFavoritesDbRepository(
        favoritesDbRepositoryImpl: FavoritesDbRepositoryImpl
    ): FavoritesDbRepository
}
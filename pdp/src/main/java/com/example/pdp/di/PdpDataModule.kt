package com.example.pdp.di

import com.example.pdp.repo.ProductRepository
import com.example.pdp.repo.ProductRepositoryImpl
import com.example.pdp.repo.RecentlyViewedRepository
import com.example.pdp.repo.RecentlyViewedRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [PdpNetworkModule::class])
internal abstract class PdpDataModule {

    @Binds
    abstract fun provideProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideRecentlyViewedRepository(recentlyViewedRepositoryImpl: RecentlyViewedRepositoryImpl): RecentlyViewedRepository
}
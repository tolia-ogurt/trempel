package com.trempel.pdp.di

import com.trempel.pdp.repo.ProductRepository
import com.trempel.pdp.repo.ProductRepositoryImpl
import com.trempel.pdp.repo.RecentlyViewedRepository
import com.trempel.pdp.repo.RecentlyViewedRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [PdpNetworkModule::class])
internal abstract class PdpDataModule {

    @Binds
    abstract fun provideProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideRecentlyViewedRepository(recentlyViewedRepositoryImpl: RecentlyViewedRepositoryImpl): RecentlyViewedRepository
}

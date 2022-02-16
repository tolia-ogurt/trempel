package com.trempel.bag.di

import com.trempel.bag.repository.BagRepository
import com.trempel.bag.repository.BagRepositoryImpl
import dagger.Binds
import dagger.Module


@Module(includes = [BagNetworkModule::class])
abstract class BagDataModule {

    @Binds
    abstract fun provideBagRepository(bagRepositoryImpl: BagRepositoryImpl): BagRepository
}
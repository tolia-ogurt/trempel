package com.trempel.bag.di

import com.trempel.bag.repository.BagNetworkRepository
import com.trempel.bag.repository.BagNetworkRepositoryImpl
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.core_network.bag_db.db.BagDbRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [BagNetworkModule::class])
abstract class BagDataModule {

    @Binds
    abstract fun provideBagNetworkRepository(bagRepositoryImpl: BagNetworkRepositoryImpl): BagNetworkRepository

    @Binds
    abstract fun provideBagDbRepository(bagDbRepositoryImpl: BagDbRepositoryImpl): BagDbRepository
}

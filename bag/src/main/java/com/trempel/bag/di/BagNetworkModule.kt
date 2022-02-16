package com.trempel.bag.di

import com.trempel.bag.service.BagService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
internal class BagNetworkModule {

    @Provides
    fun provideBagService(retrofit: Retrofit): BagService {
        return retrofit.create(BagService::class.java)
    }
}
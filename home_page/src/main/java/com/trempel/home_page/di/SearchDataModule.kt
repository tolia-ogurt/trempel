package com.trempel.home_page.di

import com.trempel.home_page.repository.SearchRepository
import com.trempel.home_page.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [SearchNetworkModule::class])
internal abstract class SearchDataModule {

    @Binds
    abstract fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
package com.trempel.home_page.di

import com.trempel.home_page.service.SearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class SearchNetworkModule {

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}

package com.trempel.categories.di

import com.trempel.categories.service.CategoryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class CategoriesNetworkModule {

    @Provides
    fun provideCategoryRepository(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }
}
package com.example.categories.di

import com.example.categories.repo.CategoryRepository
import com.example.categories.repo.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [CategoriesNetworkModule::class])
internal abstract class CategoriesDataModule {

    @Binds
    abstract fun provideCategoryService(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}
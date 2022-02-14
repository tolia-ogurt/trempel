package com.trempel.categories.di

import com.trempel.categories.repo.CategoryRepository
import com.trempel.categories.repo.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [CategoriesNetworkModule::class])
internal abstract class CategoriesDataModule {

    @Binds
    abstract fun provideCategoryService(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository
}
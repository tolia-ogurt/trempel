package com.example.categories.di

import com.example.categories.ui.categories.CategoryFragment
import com.example.categories.ui.products_in_category.CategoryProductsFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [CategoriesDataModule::class])
interface CategoriesComponent : AndroidInjector<CategoryFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<CategoryFragment>
}

@Subcomponent(modules = [CategoriesDataModule::class])
interface CategoriesProductComponent : AndroidInjector<CategoryProductsFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<CategoryProductsFragment>
}

@Module(subcomponents = [CategoriesComponent::class])
abstract class CategoryFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(CategoryFragment::class)
    abstract fun bindCategoryFragmentInjector(factory: CategoriesComponent.Factory): AndroidInjector.Factory<*>
}

@Module(subcomponents = [CategoriesProductComponent::class])
abstract class CategoryProductsFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(CategoryProductsFragment::class)
    abstract fun bindCategoryProductsFragmentInjector(factory: CategoriesProductComponent.Factory): AndroidInjector.Factory<*>
}
package com.trempel.home_page.di

import com.trempel.home_page.ui.HomePageFragment
import com.trempel.home_page.ui.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [SearchDataModule::class])
interface SearchComponent : AndroidInjector<SearchFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SearchFragment>
}

@Module(subcomponents = [SearchComponent::class])
abstract class SearchFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(SearchFragment::class)
    abstract fun bindSearchFragmentInjectorFragment(factory: SearchComponent.Factory): AndroidInjector.Factory<*>
}

@Subcomponent
interface HomePageComponent : AndroidInjector<HomePageFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<HomePageFragment>
}

@Module(subcomponents = [HomePageComponent::class])
abstract class HomePageFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(HomePageFragment::class)
    abstract fun bindHomePageInjectorFragment(factory: HomePageComponent.Factory): AndroidInjector.Factory<*>
}

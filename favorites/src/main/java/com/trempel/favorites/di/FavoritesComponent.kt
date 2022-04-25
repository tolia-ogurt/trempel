package com.trempel.favorites.di

import com.trempel.favorites.ui.FavoritesFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent
interface FavoritesComponent : AndroidInjector<FavoritesFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<FavoritesFragment>
}

@Module(subcomponents = [FavoritesComponent::class])
abstract class FavoritesFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(FavoritesFragment::class)
    abstract fun bindFavoritesFragmentInjector(factory: FavoritesComponent.Factory): AndroidInjector.Factory<*>
}
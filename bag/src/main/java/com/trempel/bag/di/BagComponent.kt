package com.trempel.bag.di

import com.trempel.bag.ui.BagFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent
interface BagComponent : AndroidInjector<BagFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<BagFragment>
}

@Module(subcomponents = [BagComponent::class])
abstract class BagFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(BagFragment::class)
    abstract fun bindBagFragmentInjector(factory: BagComponent.Factory): AndroidInjector.Factory<*>
}
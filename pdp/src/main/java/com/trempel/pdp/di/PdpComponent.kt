package com.trempel.pdp.di

import com.trempel.pdp.ui.PdpFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [PdpDataModule::class])
interface PdpComponent : AndroidInjector<PdpFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PdpFragment>
}

@Module(subcomponents = [PdpComponent::class])
abstract class PdpFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(PdpFragment::class)
    abstract fun bindPdpFragmentInjector(factory: PdpComponent.Factory): AndroidInjector.Factory<*>
}
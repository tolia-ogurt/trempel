package com.trempel.profile.di

import com.trempel.profile.ui.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [ImageSaverModule::class, ProfileDataModule::class])
interface ProfileComponent : AndroidInjector<ProfileFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<ProfileFragment>
}

@Module(subcomponents = [ProfileComponent::class])
abstract class ProfileFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(ProfileFragment::class)
    abstract fun bindProfileFragmentInjectorFragment(factory: ProfileComponent.Factory): AndroidInjector.Factory<*>
}

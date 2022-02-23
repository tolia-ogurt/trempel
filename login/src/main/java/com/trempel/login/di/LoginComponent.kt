package com.trempel.login.di

import com.trempel.login.ui.SignInFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [LoginDataModule::class])
interface LoginComponent : AndroidInjector<SignInFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SignInFragment>
}

@Module(subcomponents = [LoginComponent::class])
abstract class LoginFragmentModule {

    @Binds
    @IntoMap
    @ClassKey(SignInFragment::class)
    abstract fun bindLoginFragmentInjectorFragment(factory: LoginComponent.Factory): AndroidInjector.Factory<*>
}

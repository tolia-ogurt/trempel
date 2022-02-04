package com.example.login.di

import com.example.login.ui.SignInFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent(modules = [LoginNetworkModule::class])
interface LoginComponent : AndroidInjector<SignInFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SignInFragment>
}

@Module(subcomponents = [LoginComponent::class])
abstract class LoginFragmentModule{

    @Binds
    @IntoMap
    @ClassKey(SignInFragment::class)
    abstract fun bindLoginFragmentInjectorFragment(factory: LoginComponent.Factory): AndroidInjector.Factory<*>
}
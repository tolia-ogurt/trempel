package com.example.trempel.di

import android.content.Context
import com.example.trempel.PdpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
internal interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(pdpFragment: PdpFragment)
}


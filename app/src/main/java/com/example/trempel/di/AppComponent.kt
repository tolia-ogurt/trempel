package com.example.trempel.di

import android.content.Context
import com.example.categories.di.CategoryFragmentModule
import com.example.categories.di.CategoryProductsFragmentModule
import com.example.login.di.LoginFragmentModule
import com.example.pdp.di.PdpFragmentModule
import com.example.trempel.MyApplication
import com.example.trempel.ui.main_activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        LoginFragmentModule::class,
        PdpFragmentModule::class,
        CategoryFragmentModule::class,
        CategoryProductsFragmentModule::class
    ]
)
internal interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MyApplication)
    fun inject(mainActivity: MainActivity)
}

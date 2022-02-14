package com.trempel.di

import android.content.Context
import com.trempel.categories.di.CategoryFragmentModule
import com.trempel.categories.di.CategoryProductsFragmentModule
import com.trempel.login.di.LoginFragmentModule
import com.trempel.pdp.di.PdpFragmentModule
import com.trempel.MyApplication
import com.trempel.ui.main_activity.MainActivity
import dagger.*
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


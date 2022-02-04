package com.example.trempel.di

import android.content.Context
import com.example.login.di.LoginComponent
import com.example.login.di.LoginFragmentModule
import com.example.trempel.ui.categories.CategoryFragment
import com.example.login.ui.SignInFragment
import com.example.trempel.MyApplication
import com.example.trempel.ui.main_activity.MainActivity
import com.example.trempel.ui.pdp.PdpFragment
import com.example.trempel.ui.products_in_category.CategoryProductsFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        LoginFragmentModule::class
    ]
)
internal interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(application: MyApplication)
    fun inject(pdpFragment: PdpFragment)
    fun inject(productsInCategory: CategoryProductsFragment)
    fun inject(categoryFragment: CategoryFragment)
    fun inject(mainActivity: MainActivity)
}

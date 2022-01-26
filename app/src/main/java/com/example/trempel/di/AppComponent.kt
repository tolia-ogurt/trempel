package com.example.trempel.di

import android.content.Context
import com.example.trempel.ui.categories.CategoryFragment
import com.example.trempel.ui.login.SignInFragment
import com.example.trempel.ui.main_activity.MainActivity
import com.example.trempel.ui.pdp.PdpFragment
import com.example.trempel.ui.products_in_category.CategoryProductsFragment
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
    fun inject(productsInCategory: CategoryProductsFragment)
    fun inject(categoryFragment: CategoryFragment)
    fun inject(loginFragment: SignInFragment)
    fun inject(mainActivity: MainActivity)
}


package com.example.trempel.di

import android.content.Context
import com.example.trempel.ui.categories.CategoryFragment
import com.example.trempel.ui.mens_category.MensCategoryFragment
import com.example.trempel.ui.pdp.PdpFragment
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
    fun inject(mensCategoryFragment: MensCategoryFragment)
    fun inject(categoryFragment: CategoryFragment)
}


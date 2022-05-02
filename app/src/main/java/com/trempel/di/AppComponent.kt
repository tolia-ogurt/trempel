package com.trempel.di

import android.content.Context
import com.trempel.MyApplication
import com.trempel.bag.di.BagDataModule
import com.trempel.bag.di.BagFragmentModule
import com.trempel.categories.di.CategoryFragmentModule
import com.trempel.categories.di.CategoryProductsFragmentModule
import com.trempel.favorites.di.FavoritesDataModule
import com.trempel.favorites.di.FavoritesFragmentModule
import com.trempel.home_page.di.HomePageFragmentModule
import com.trempel.home_page.di.SearchFragmentModule
import com.trempel.login.di.LoginFragmentModule
import com.trempel.pdp.di.PdpFragmentModule
import com.trempel.profile.di.ProfileFragmentModule
import com.trempel.ui.main_activity.MainActivity
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
        CategoryProductsFragmentModule::class,
        BagFragmentModule::class,
        BagDataModule::class,
        SearchFragmentModule::class,
        HomePageFragmentModule::class,
        ProfileFragmentModule::class,
        HomePageFragmentModule::class,
        FavoritesFragmentModule::class,
        FavoritesDataModule::class
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

package com.example.trempel.di

import com.example.trempel.network.service.CategoryService
import android.content.Context
import androidx.room.Room
import com.example.trempel.db.AppDataBase
import com.example.trempel.network.service.ProductService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://fakestoreapi.com/"

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomRecentlyDataBase(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "recently_viewed"
    ).build()

    @Provides
    @Singleton
    fun provideRecentlyViewedDao(db: AppDataBase) = db.getRecentlyViewedDao()
}
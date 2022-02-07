package com.example.pdp.di

import android.content.Context
import androidx.room.Room
import com.example.pdp.db.AppDataBase
import com.example.pdp.repo.ProductRepository
import com.example.pdp.repo.ProductRepositoryImpl
import com.example.pdp.service.ProductService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal class PdpNetworkModule {

    @Provides
    fun provideRoomRecentlyDataBase(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "recently_viewed"
    ).build()

    @Provides
    fun provideRecentlyViewedDao(db: AppDataBase) = db.getRecentlyViewedDao()

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}
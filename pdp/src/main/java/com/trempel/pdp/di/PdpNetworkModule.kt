package com.trempel.pdp.di

import android.content.Context
import androidx.room.Room
import com.example.pdp.R
import com.trempel.pdp.db.AppDataBase
import com.trempel.pdp.repo.ProductRepository
import com.trempel.pdp.repo.ProductRepositoryImpl
import com.trempel.pdp.service.ProductService
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
        AppDataBase::class.java, TABLE_NAME_DB
    ).build()

    @Provides
    fun provideRecentlyViewedDao(db: AppDataBase) = db.getRecentlyViewedDao()

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    companion object {

        const val TABLE_NAME_DB = "recently_viewed"
    }
}
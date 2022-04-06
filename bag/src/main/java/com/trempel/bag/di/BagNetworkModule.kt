package com.trempel.bag.di

import android.content.Context
import androidx.room.Room
import com.trempel.core_network.bag_db.db.AppDataBase
import com.trempel.bag.service.BagService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BagNetworkModule {

    @Provides
    fun provideRoomBagDataBase(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, TABLE_NAME_DB
    ).build()

    @Provides
    fun provideBagDao(db: AppDataBase) = db.getBagDao()

    @Provides
    fun provideBagService(retrofit: Retrofit): BagService {
        return retrofit.create(BagService::class.java)
    }

    companion object {
        const val TABLE_NAME_DB = "bag"
    }
}
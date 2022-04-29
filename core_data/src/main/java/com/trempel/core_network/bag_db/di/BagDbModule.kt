package com.trempel.core_network.bag_db.di

import android.content.Context
import androidx.room.Room
import com.trempel.core_network.bag_db.db.AppDataBase
import dagger.Module
import dagger.Provides

@Module
class BagDbModule {

    @Provides
    fun provideRoomBagDataBase(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, TABLE_NAME_DB
    ).build()

    @Provides
    fun provideBagDao(db: AppDataBase) = db.getBagDao()

    companion object {
        const val TABLE_NAME_DB = "bag"
    }
}

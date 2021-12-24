package com.example.trempel.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RecentlyViewedDao {

    @Insert(onConflict = REPLACE)
    fun addRecentlyProductViewed(recentlyViewed: RecentlyViewed): Completable

    @Query("SELECT * FROM recently_viewed WHERE id <>:id ORDER BY created_at DESC LIMIT 5")
    fun getRecentlyProductsViewed(id: Int): Single<MutableList<RecentlyViewed>>
}
package com.trempel.core_network.bag_db.db

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface BagDao {

    @Insert(onConflict = ABORT)
    suspend fun addProductToBag(bag: BagEntity)

    @Query("SELECT * FROM bag ORDER BY created_at")
    suspend fun getProductsFromBag(): List<BagEntity>

    @Query("DELETE FROM bag WHERE productId = :productId")
    suspend fun deleteProductFromBagById(productId: Int)

    @Update
    suspend fun updateQuantity(vararg bagEntity: BagEntity)

    @Query("UPDATE bag SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun increaseQuantity(productId: Int)
}
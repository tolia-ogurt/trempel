package com.trempel.core_network.bag_db.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import androidx.room.Update

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

package com.trempel.core_network.favorites_db.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addProductToFavorites(favorites: FavoritesEntity)

    @Query("SELECT * FROM favorites ORDER BY created_at")
    suspend fun getProductsFromFavorites(): List<FavoritesEntity>

    @Query("DELETE FROM favorites WHERE productId = :productId")
    suspend fun deleteProductFromFavoritesById(productId: Int)

    @Update
    suspend fun updateQuantity(vararg favoritesEntity: FavoritesEntity)

    @Query("UPDATE favorites SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun increaseQuantity(productId: Int)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE productId = :productId)")
    fun isFavorite(productId: Int): LiveData<Boolean>
}
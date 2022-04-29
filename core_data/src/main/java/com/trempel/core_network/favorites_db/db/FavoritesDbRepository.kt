package com.trempel.core_network.favorites_db.db

import androidx.lifecycle.LiveData

interface FavoritesDbRepository {

    suspend fun addProductToFavorites(productId: Int)
    suspend fun getProductsFromDbFavorites(): List<FavoritesEntity>
    suspend fun deleteProductFromDbFavoritesById(productId: Int)
    suspend fun updateQuantity(vararg favoritesEntity: FavoritesEntity)
    fun isFavorite(productId: Int): LiveData<Boolean>
}

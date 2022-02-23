package com.trempel.core_network.favorites_db.db

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import javax.inject.Inject

class FavoritesDbRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesDbRepository {

    override suspend fun addProductToFavorites(productId: Int) {
        try {
            favoritesDao.addProductToFavorites(
                FavoritesEntity(
                    productId = productId,
                    quantity = START_ITEM_QUANTITY,
                    createdAt = System.currentTimeMillis()
                )
            )
        } catch (exception: SQLiteConstraintException) {
            favoritesDao.increaseQuantity(productId)
        }
    }

    override suspend fun getProductsFromDbFavorites(): List<FavoritesEntity> {
        return favoritesDao.getProductsFromFavorites()
    }

    override suspend fun deleteProductFromDbFavoritesById(productId: Int) {
        favoritesDao.deleteProductFromFavoritesById(productId)
    }

    override suspend fun updateQuantity(vararg favoritesEntity: FavoritesEntity) {
        favoritesDao.updateQuantity(*favoritesEntity)
    }

    override fun isFavorite(productId: Int): LiveData<Boolean> {
        return favoritesDao.isFavorite(productId)
    }

    companion object {
        const val START_ITEM_QUANTITY = 1
    }
}

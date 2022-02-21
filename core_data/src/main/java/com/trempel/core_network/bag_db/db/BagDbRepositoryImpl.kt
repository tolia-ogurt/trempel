package com.trempel.core_network.bag_db.db

import android.database.sqlite.SQLiteConstraintException
import javax.inject.Inject

class BagDbRepositoryImpl @Inject constructor(
    private val bagDao: BagDao
) : BagDbRepository {

    override suspend fun addProductToBag(productId: Int) {
        try {
            bagDao.addProductToBag(
                BagEntity(
                    productId = productId,
                    quantity = 1,
                    createdAt = System.currentTimeMillis()
                )
            )
        } catch (exception: SQLiteConstraintException) {
            bagDao.increaseQuantity(productId)
        }
    }

    override suspend fun getProductsFromDbBag(): List<BagEntity> {
        return bagDao.getProductsFromBag()
    }

    override suspend fun deleteProductFromDbBagById(productId: Int) {
        bagDao.deleteProductFromBagById(productId)
    }

    override suspend fun updateQuantity(vararg bagEntity: BagEntity) {
        bagDao.updateQuantity(*bagEntity)
    }
}
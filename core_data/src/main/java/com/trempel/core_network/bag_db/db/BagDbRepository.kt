package com.trempel.core_network.bag_db.db

interface BagDbRepository {

    suspend fun addProductToBag(productId: Int)
    suspend fun getProductsFromDbBag(): List<BagEntity>
    suspend fun deleteProductFromDbBagById(productId: Int)
    suspend fun updateQuantity(vararg bagEntity: BagEntity)
}
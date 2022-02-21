package com.trempel.bag.repository

import com.trempel.core_network.ProductModel

interface BagNetworkRepository {

    //    suspend fun getAllCarts(): List<BagDomainModel>
    suspend fun getProduct(productId: Int): ProductModel
}
package com.trempel.bag.repository

import com.trempel.core_network.ProductModel

interface BagNetworkRepository {

    suspend fun getProduct(productId: Int): ProductModel
}
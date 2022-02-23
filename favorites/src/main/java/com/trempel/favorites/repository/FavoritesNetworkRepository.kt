package com.trempel.favorites.repository

import com.trempel.core_network.ProductModel

interface FavoritesNetworkRepository {

    suspend fun getProduct(productId: Int): ProductModel
}

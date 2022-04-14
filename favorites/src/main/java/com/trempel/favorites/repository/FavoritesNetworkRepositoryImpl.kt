package com.trempel.favorites.repository

import com.trempel.core_network.ProductModel
import com.trempel.favorites.service.FavoritesService
import javax.inject.Inject

class FavoritesNetworkRepositoryImpl @Inject constructor(
    private val favoritesService: FavoritesService,
) : FavoritesNetworkRepository {

    override suspend fun getProduct(productId: Int): ProductModel {
        return favoritesService.getProduct(productId)
    }
}
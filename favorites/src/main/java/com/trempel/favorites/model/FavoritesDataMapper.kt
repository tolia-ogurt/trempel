package com.trempel.favorites.model

import com.trempel.core_network.ProductModel
import com.trempel.core_network.favorites_db.db.FavoritesEntity

internal fun ProductModel.toFavoritesDomainModel(productQuantity: Int): FavoritesDomainModel {
    return FavoritesDomainModel(
        id = this.id,
        image = this.image,
        price = this.price,
        title = this.title,
        quantity = productQuantity
    )
}

internal fun FavoritesDomainModel.toFavoritesEntity(quantity: Int = 1): FavoritesEntity {
    return FavoritesEntity(
        productId = this.id,
        quantity = quantity,
        createdAt = System.currentTimeMillis()
    )
}
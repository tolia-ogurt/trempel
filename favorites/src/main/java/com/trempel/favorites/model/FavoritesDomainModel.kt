package com.trempel.favorites.model

data class FavoritesDomainModel(
    val id: Int,
    val image: String,
    val price: Float,
    val title: String,
    val quantity: Int
)

package com.example.trempel.network.model

internal data class CategoryModelItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingMensCategory,
    val title: String
)

internal data class RatingMensCategory(
    val count: Int,
    val rate: Double
)
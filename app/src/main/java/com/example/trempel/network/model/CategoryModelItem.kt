package com.example.trempel.network.model

internal data class CategoryModelItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingCategory,
    val title: String
)

internal data class RatingCategory(
    val count: Int,
    val rate: Double
)
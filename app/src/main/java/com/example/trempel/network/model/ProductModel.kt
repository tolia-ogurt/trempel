package com.example.trempel.network.model

internal data class ProductModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Float,
    val rating: Rating,
    val title: String
)

internal data class Rating(
    val count: Int,
    val rate: Float
)
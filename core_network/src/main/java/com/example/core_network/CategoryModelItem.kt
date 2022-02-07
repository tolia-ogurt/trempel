package com.example.core_network

data class CategoryModelItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingCategory,
    val title: String
)

data class RatingCategory(
    val count: Int,
    val rate: Double
)
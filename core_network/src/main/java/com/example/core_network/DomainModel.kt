package com.example.core_network

data class DomainModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Float,
    val rating: RatingDomain,
    val title: String
)

data class RatingDomain(
    val count: Int,
    val rate: Float
)

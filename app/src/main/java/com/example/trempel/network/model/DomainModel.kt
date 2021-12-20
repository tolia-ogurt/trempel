package com.example.trempel.network.model

internal data class DomainModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Float,
    val rating: RatingDomain,
    val title: String
)

internal data class RatingDomain(
    val count: Int,
    val rate: Float
)

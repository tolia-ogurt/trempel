package com.trempel.core_network

data class BagModelItem(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)

data class Product(
    val productId: Int,
    val quantity: Int
)
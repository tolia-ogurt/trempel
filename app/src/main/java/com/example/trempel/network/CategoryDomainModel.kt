package com.example.trempel.network

import com.example.trempel.R

enum class CategoryDomainModel(val title: String, val image: Int) {
    ALL_PRODUCTS("all products", R.drawable.image_mens_category),
    ELECTRONICS("electronics", R.drawable.image_electronics_category),
    JEWELERY("jewelery", R.drawable.image_jewelery_category),
    MENS_CLOTHING("men's clothing", R.drawable.image_mens_category),
    WOMENS_CLOTHING("women's clothing", R.drawable.image_womens_category),
}
package com.trempel.favorites.service

import com.trempel.core_network.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface FavoritesService {

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): ProductModel
}
package com.trempel.bag.service

import com.trempel.core_network.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface BagService {

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): ProductModel
}

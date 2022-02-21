package com.trempel.bag.service

import com.trempel.core_network.BagModelItem
import com.trempel.core_network.ProductModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BagService {

//    @GET("carts")
//    suspend fun getAllCarts(): List<BagModelItem>

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") productId: Int): ProductModel
}
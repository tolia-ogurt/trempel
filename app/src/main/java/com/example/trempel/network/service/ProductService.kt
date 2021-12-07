package com.example.trempel.network.service

import com.example.trempel.network.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductService {

    @GET("/products/{id}")
    fun getProduct(@Path("id") productId: Int): Call<ProductModel>
}
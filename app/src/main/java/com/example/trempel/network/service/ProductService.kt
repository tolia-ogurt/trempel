package com.example.trempel.network.service

import com.example.trempel.network.model.ProductModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductService {

    @GET("/products/{id}")
    fun getProduct(@Path("id") productId: Int): Single<ProductModel>
}
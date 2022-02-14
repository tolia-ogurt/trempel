package com.trempel.categories.service

import com.trempel.core_network.ProductModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CategoryService {

    @GET("/products/categories")
    fun getAllCategories(): Single<List<String>>

    @GET("/products/category/{category}")
    fun getCategoryProducts(@Path("category") categoryName: String): Single<List<ProductModel>>

    @GET("/products")
    fun getAllProducts(): Single<List<ProductModel>>
}
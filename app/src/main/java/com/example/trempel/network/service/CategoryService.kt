package com.example.trempel.network.service

import com.example.trempel.network.model.CategoryModelItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CategoryService {

    @GET("/products/categories")
    fun getAllCategories(): Single<List<String>>

    @GET("/products/category/{category}")
    fun getCategoryProducts(@Path("category") categoryName: String): Single<List<CategoryModelItem>>

    @GET("/products")
    fun getAllProducts(): Single<List<CategoryModelItem>>
}
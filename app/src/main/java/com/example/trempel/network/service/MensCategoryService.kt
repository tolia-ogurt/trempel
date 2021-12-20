package com.example.trempel.network.service

import com.example.trempel.network.model.CategoryModelItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MensCategoryService {

    @GET("/products/category/{category}")
    fun getProductForCategory(@Path("category") nameCategory: String): Single<List<CategoryModelItem>>
}
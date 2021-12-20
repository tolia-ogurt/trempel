package com.example.trempel.network.service

import io.reactivex.Single
import retrofit2.http.GET

interface CategoryService {

    @GET("/products/categories")
    fun getAllCategories(): Single<List<String>>
}
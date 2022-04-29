package com.trempel.home_page.service

import com.trempel.core_network.ProductModel
import retrofit2.http.GET

interface SearchService {

    @GET("products")
    suspend fun getAllProducts(): List<ProductModel>
}

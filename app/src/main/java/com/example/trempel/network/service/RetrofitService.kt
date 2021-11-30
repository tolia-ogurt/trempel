package com.example.trempel.network.service

import com.example.trempel.network.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/products/2")
    fun getProduct(): Call<ProductModel>
}
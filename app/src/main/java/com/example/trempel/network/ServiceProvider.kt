package com.example.trempel.network

import com.example.trempel.network.service.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {

    private const val BASE_URL = "https://fakestoreapi.com/"
    private var retrofit: Retrofit? = null

    fun getRetrofitService(): RetrofitService? {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit?.create(RetrofitService::class.java)
    }
}
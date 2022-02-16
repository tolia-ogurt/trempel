package com.trempel.bag.service

import com.trempel.core_network.BagModelItem
import retrofit2.Response
import retrofit2.http.GET

interface BagService {

    @GET("carts")
    suspend fun getAllCarts(): Response<List<BagModelItem>>
}
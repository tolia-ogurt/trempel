package com.trempel.bag.repository

import com.trempel.core_network.BagModelItem
import retrofit2.Response

interface BagRepository {

    suspend fun getAllCarts(): Response<List<BagModelItem>>
}
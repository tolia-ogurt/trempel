package com.trempel.bag.repository

import com.trempel.bag.service.BagService
import com.trempel.core_network.BagModelItem
import retrofit2.Response
import javax.inject.Inject

class BagRepositoryImpl @Inject constructor(
    private val bagService: BagService
) : BagRepository {

    override suspend fun getAllCarts(): Response<List<BagModelItem>> {
        return bagService.getAllCarts()
    }
}
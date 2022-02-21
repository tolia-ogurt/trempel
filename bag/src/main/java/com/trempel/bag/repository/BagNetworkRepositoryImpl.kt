package com.trempel.bag.repository

import com.trempel.bag.model.BagDomainModel
import com.trempel.bag.model.toBagDomainModel
import com.trempel.bag.service.BagService
import com.trempel.core_network.ProductModel
import javax.inject.Inject

class BagNetworkRepositoryImpl @Inject constructor(
    private val bagService: BagService,
) : BagNetworkRepository {


    override suspend fun getProduct(productId: Int): ProductModel {
        return bagService.getProduct(productId)
    }
}
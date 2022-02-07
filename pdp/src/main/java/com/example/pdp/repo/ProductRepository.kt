package com.example.pdp.repo

import com.example.core_network.DomainModel
import io.reactivex.Single

interface ProductRepository {

    fun getProduct(productId: Int): Single<DomainModel>
}
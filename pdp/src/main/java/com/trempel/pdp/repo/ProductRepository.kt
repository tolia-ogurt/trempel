package com.trempel.pdp.repo

import com.trempel.pdp.model.ProductDomainModel
import io.reactivex.Single

interface ProductRepository {

    fun getProduct(productId: Int): Single<ProductDomainModel>
}

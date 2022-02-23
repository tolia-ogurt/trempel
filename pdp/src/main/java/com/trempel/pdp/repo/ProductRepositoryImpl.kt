package com.trempel.pdp.repo

import com.trempel.pdp.model.ProductDomainModel
import com.trempel.pdp.model.toDomainModel
import com.trempel.pdp.service.ProductService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val retrofitService: ProductService
) : ProductRepository {

    override fun getProduct(productId: Int): Single<ProductDomainModel> {
        return retrofitService.getProduct(productId)
            .map { it.toDomainModel() }
            .subscribeOn(Schedulers.io())
    }
}

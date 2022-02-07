package com.example.pdp.repo

import com.example.pdp.service.ProductService
import com.example.core_network.DomainModel
import com.example.trempel.network.toDomainModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class ProductRepositoryImpl @Inject constructor(
    private val retrofitService: ProductService
) : ProductRepository {

    override fun getProduct(productId: Int): Single<DomainModel> {
        return retrofitService.getProduct(productId)
            .map { it.toDomainModel() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
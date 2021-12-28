package com.example.trempel

import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.service.ProductService
import com.example.trempel.network.toDomainModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


internal class ProductRepository @Inject constructor(private val retrofitService: ProductService) {

    fun getProduct(
        productId: Int
    ): Single<DomainModel> {
        return retrofitService.getProduct(productId)
            .map { it.toDomainModel() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
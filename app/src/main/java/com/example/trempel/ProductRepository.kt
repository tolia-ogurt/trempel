package com.example.trempel

import com.example.trempel.network.ProductDataMapper
import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.model.RatingDomain
import com.example.trempel.network.service.ProductService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val PRODUCT_ID = 2

internal class ProductRepository @Inject constructor(private val retrofitService: ProductService) {

    fun getProduct(
        productId: Int = PRODUCT_ID
    ): Single<DomainModel> {
        return retrofitService.getProduct(productId)
            .map { ProductDataMapper.map(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
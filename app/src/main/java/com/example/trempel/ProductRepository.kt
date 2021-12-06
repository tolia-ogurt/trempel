package com.example.trempel

import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.service.ProductService
import retrofit2.Call
import javax.inject.Inject

internal class ProductRepository @Inject constructor(private val retrofitService: ProductService) {

    fun getProduct(productId: Int): Call<ProductModel> {
        return retrofitService.getProduct(productId)
    }
}

package com.example.trempel

import com.example.trempel.network.ServiceProvider
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.service.RetrofitService
import retrofit2.Call

internal class ServiceRepository : RetrofitService {

    private val retrofitService = ServiceProvider.getRetrofitService()

    override fun getProduct(productId: Int): Call<ProductModel> {
        return retrofitService.getProduct(productId)
    }
}

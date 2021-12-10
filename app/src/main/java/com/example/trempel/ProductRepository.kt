package com.example.trempel

import android.util.Log
import com.example.trempel.network.ProductDataMapper
import com.example.trempel.network.model.DomainModel
import com.example.trempel.network.model.ProductModel
import com.example.trempel.network.service.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val PRODUCT_ID = 2

internal class ProductRepository @Inject constructor(private val retrofitService: ProductService) {

    fun getProduct(
        serviceCallback: ServiceCallback<DomainModel>,
        productId: Int = PRODUCT_ID
    ) {
        retrofitService.getProduct(productId).enqueue(object : Callback<ProductModel> {
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    serviceCallback.onSuccess(ProductDataMapper.map(body))
                }
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                serviceCallback.onFailure(t)
                Log.e(this@ProductRepository.javaClass.simpleName, t.message, t)
            }
        })
    }
}

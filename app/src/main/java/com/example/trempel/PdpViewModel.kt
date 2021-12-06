package com.example.trempel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.network.model.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class PdpViewModel : ViewModel() {

    private val serviceRepository = ServiceRepository()
    private val _product = MutableLiveData<ProductModel>()
    val product: LiveData<ProductModel> get() = _product

    companion object {
        private const val PRODUCT_ID = 2
    }

    fun loadProduct() {
        serviceRepository.getProduct(PRODUCT_ID).enqueue(object : Callback<ProductModel> {
            override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {
                response.body()?.let {
                    _product.value = it
                    Log.e(this.javaClass.name, "onResponse")
                } ?: onFailure(call, Exception("Response body is null"))
            }

            override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                Log.e(this.javaClass.name, t.toString())
            }
        })
    }
}

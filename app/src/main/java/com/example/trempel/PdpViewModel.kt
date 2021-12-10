package com.example.trempel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.network.model.DomainModel
import javax.inject.Inject

internal class PdpViewModel @Inject constructor(
    private val serviceRepository: ProductRepository
) : ViewModel() {

    private val _product = MutableLiveData<DomainModel>()
    val product: LiveData<DomainModel> get() = _product
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val productCallback = object : ServiceCallback<DomainModel> {
        override fun onSuccess(response: DomainModel) {
            _product.value = response
        }

        override fun onFailure(t: Throwable) {
            _errorLiveData.value = t.toString()
        }
    }

    fun loadProduct() {
        serviceRepository.getProduct(productCallback)
    }
}

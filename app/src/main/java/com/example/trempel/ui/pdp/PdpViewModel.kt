package com.example.trempel.ui.pdp

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.ProductRepository
import com.example.trempel.network.model.DomainModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class PdpViewModel @Inject constructor(
    private val serviceRepository: ProductRepository
) : ViewModel() {

    private val _product = MutableLiveData<DomainModel>()
    val product: LiveData<DomainModel> get() = _product
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    var isInProgress = ObservableBoolean(true)

    fun loadProduct(productId: Int) {
        disposable = serviceRepository.getProduct(productId)
            .doOnSubscribe {
               isInProgress.set(true)
            }
            .doFinally {
                isInProgress.set(false)
            }
            .doOnError {
                Log.d("TAG", "loadProduct: $it")
            }
            .subscribe({ response ->
                _product.value = response
            }, { error ->
                _errorLiveData.value = error.message
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}

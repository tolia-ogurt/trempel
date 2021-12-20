package com.example.trempel

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trempel.network.ProductDataMapper
import com.example.trempel.network.model.DomainModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
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

    fun loadProduct() {
        disposable = serviceRepository.getProduct()
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

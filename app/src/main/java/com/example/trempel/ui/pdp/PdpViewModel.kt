package com.example.trempel.ui.pdp

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.ProductRepository
import com.example.trempel.RecentlyViewedRepository
import com.example.trempel.db.RecentlyViewed
import com.example.trempel.network.model.DomainModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class PdpViewModel @Inject constructor(
    private val serviceRepository: ProductRepository,
    private val roomRepository: RecentlyViewedRepository
) : ViewModel() {

    private val _product = MutableLiveData<DomainModel>()
    val product: LiveData<DomainModel> get() = _product
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private val disposable = CompositeDisposable()
    private val _allRecentlyViewedProduct = MutableLiveData<List<RecentlyViewed>>()
    val allRecentlyViewedProduct: LiveData<List<RecentlyViewed>> get() = _allRecentlyViewedProduct
    var isInProgress = ObservableBoolean(true)

    fun loadProduct(productId: Int) {
        disposable += serviceRepository.getProduct(productId)
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
                addRecentlyViewedProduct(response)
            }, { error ->
                _errorLiveData.value = error.message
            })
    }

    fun getRecentlyViewedProduct(idProduct: Int) {
        disposable += roomRepository.getLatestRecentlyViewed(idProduct)
            .subscribe({
                _allRecentlyViewedProduct.value = it
            }, {
                Log.e("Room live data", "Error")
            })
    }

    private fun addRecentlyViewedProduct(productDomainModel: DomainModel) {
        disposable += roomRepository.addRecentlyViewed(productDomainModel)
            .subscribe()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable){
        this.add(disposable)
    }
}

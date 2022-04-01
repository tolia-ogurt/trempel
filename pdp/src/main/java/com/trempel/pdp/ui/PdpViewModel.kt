package com.trempel.pdp.ui

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.trempel.core_ui.RecyclerItem
import com.example.pdp.BR
import com.example.pdp.R
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.pdp.model.ProductDomainModel
import com.trempel.pdp.repo.ProductRepository
import com.trempel.pdp.repo.RecentlyViewedRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class PdpViewModel @Inject constructor(
    private val serviceRepository: ProductRepository,
    private val roomRepository: RecentlyViewedRepository,
    private val bagDbRepository: BagDbRepository
) : ViewModel() {

    private val _recentlyViewed = MutableLiveData<List<RecyclerItem>>()
    val recentlyViewed: LiveData<List<RecyclerItem>> get() = _recentlyViewed
    private val _product = MutableLiveData<ProductDomainModel>()
    val product: LiveData<ProductDomainModel> get() = _product
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private val disposable = CompositeDisposable()
    var isInProgress = ObservableBoolean(true)

    fun loadProduct(productId: Int) {
        disposable += serviceRepository.getProduct(productId)
            .observeOn(AndroidSchedulers.mainThread())
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _recentlyViewed.value = response.map { RecentlyViewedItemViewModel(it) }
                    .map { it.toRecyclerItem() }
            }, {
                Log.e("Room live data", "Error")
            })
    }

    private fun addRecentlyViewedProduct(productDomainModel: ProductDomainModel) {
        disposable += roomRepository.addRecentlyViewed(productDomainModel)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.e("$javaClass.name", "addRecentlyViewedProduct DONE")
                }, {
                    Log.e("$javaClass.name", "addRecentlyViewedProduct ERROR")
                }
            )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }

    private fun RecentlyViewedItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.recentlyView,
        layoutId = R.layout.recently_viewed_item
    )

    fun addProductToBag() {
        viewModelScope.launch {
            product.value?.id?.let { bagDbRepository.addProductToBag(it) }
        }
    }
}

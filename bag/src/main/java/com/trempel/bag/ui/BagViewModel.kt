package com.trempel.bag.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import com.trempel.bag.R
import com.trempel.bag.model.toBagDomainModel
import com.trempel.bag.model.toBagEntity
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.bag.repository.BagNetworkRepository
import com.trempel.core_ui.RecyclerItem
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import kotlinx.coroutines.launch
import javax.inject.Inject

class BagViewModel @Inject constructor(
    private val bagNetworkRepository: BagNetworkRepository,
    private val bagDbRepository: BagDbRepository
) : ViewModel(), DefaultLifecycleObserver {

    private val _bagItems = MutableLiveData<List<RecyclerItem>>()
    val bagItems: LiveData<List<RecyclerItem>> get() = _bagItems
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    val quantity = MediatorLiveData<Int>()

    private val _total = MutableLiveData<Float>()
    val total: LiveData<Float> get() = _total

    private val deleteObserver = Observer<Int> { productId ->
        viewModelScope.launch {
            bagDbRepository.deleteProductFromDbBagById(productId)
            _bagItems.value = _bagItems.value
                ?.filter { (it.data as BagItemViewModel).item.id != productId }
        }
    }

    init {
        quantity.addSource(_bagItems) {
            quantity.value = getQuantitySum()
            _total.value = getFinalPrice()
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            runCatching {
                bagDbRepository.getProductsFromDbBag()
                    .map { bagNetworkRepository.getProduct(it.productId) to it.quantity }
                    .map { it.first.toBagDomainModel(it.second) }
                    .map { BagItemViewModel(it) }
                    .onEach {
                        quantity.addSource(it.quantity) {
                            quantity.value = getQuantitySum()
                            _total.value = getFinalPrice()
                        }
                    }.onEach {
                        it.deleteLiveData.observeForever(deleteObserver)
                    }
                    .map { it.toRecyclerItem() }
            }.onFailure { error ->
                _errorLiveData.value = error as? TrempelException
            }.onSuccess {
                _bagItems.value = it
            }
        }
    }

    private fun getQuantitySum(): Int? {
        return _bagItems.value?.map { it.data as BagItemViewModel }
            ?.sumOf { it.quantity.value ?: 0 }

    }

    private fun getFinalPrice(): Float? {
        return _bagItems.value?.map { it.data as BagItemViewModel }
            ?.map { (it.quantity.value ?: 0) * it.item.price }
            ?.sum()
    }

    private fun BagItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.item,
        layoutId = R.layout.bag_item
    )

    override fun onCleared() {
        _bagItems.value?.map { it.data as BagItemViewModel }
            ?.forEach { it.deleteLiveData.removeObserver(deleteObserver) }
        super.onCleared()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        viewModelScope.launch {
            _bagItems.value?.let { bagItems ->
                bagDbRepository.updateQuantity(*bagItems.map { it.data as BagItemViewModel }
                    .map { it.item.toBagEntity(it.quantity.value ?: 0) }.toTypedArray())
            }
        }
    }
}
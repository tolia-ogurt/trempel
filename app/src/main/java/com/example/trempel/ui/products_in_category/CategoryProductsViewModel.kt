package com.example.trempel.ui.products_in_category

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.CategoryRepository
import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.model.CategoryModelItem
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class CategoryProductsViewModel @Inject constructor(
    private val serviceRepository: CategoryRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<CategoryModelItem>>()
    val items: LiveData<List<CategoryModelItem>> get() = _items
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    val isInProgressTemp = ObservableBoolean(true)

    fun loadProduct(category: CategoryDomainModel) {
        disposable = serviceRepository.getProducts(category)
            .doOnSubscribe {
                isInProgressTemp.set(true)
            }
            .doFinally {
                isInProgressTemp.set(false)
            }
            .subscribe({ response ->
                _items.value = response
            }, {
                _errorLiveData.value = it.message
                Log.e("Categories all", it.stackTraceToString())
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
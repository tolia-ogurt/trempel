package com.example.trempel.ui.products_in_category

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.CategoryRepository
import com.example.trempel.R
import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.ui.RecyclerItem
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class CategoryProductsViewModel @Inject constructor(
    private val serviceRepository: CategoryRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<RecyclerItem>>()
    val items: LiveData<List<RecyclerItem>> get() = _items
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
                _items.value = response.map { CategoryProductItemViewModel(it) }
                    .map { it.toRecyclerItem() }
            }, { error ->
                _errorLiveData.value = error.message
                Log.e("Categories all", error.stackTraceToString())
            })
    }

    private fun CategoryProductItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.viewModel,
        layoutId = R.layout.category_products_item
    )

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
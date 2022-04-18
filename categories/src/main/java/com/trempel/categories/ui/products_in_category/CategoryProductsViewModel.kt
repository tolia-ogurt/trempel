package com.trempel.categories.ui.products_in_category

import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.categories.R
import com.trempel.categories.repo.CategoryRepository
import com.trempel.categories.model.CategoryDomainModel
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.core_ui.RecyclerItem
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CategoryProductsViewModel @Inject constructor(
    private val serviceRepository: CategoryRepository,
    private val bagDbRepository: BagDbRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<RecyclerItem>>()
    val items: LiveData<List<RecyclerItem>> get() = _items
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    private var disposable: Disposable? = null
    val isInProgressTemp = ObservableBoolean(true)

    fun loadProduct(category: CategoryDomainModel) {
        disposable = serviceRepository.getAllProducts(category)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isInProgressTemp.set(true)
            }
            .doFinally {
                isInProgressTemp.set(false)
            }
            .subscribe({ response ->
                _items.value = response.map { CategoryProductItemViewModel(it, bagDbRepository) }
                    .map { it.toRecyclerItem() }
            }, { error ->
                _errorLiveData.value = error as? TrempelException
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
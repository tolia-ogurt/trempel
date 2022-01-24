package com.example.trempel.ui.categories

import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.CategoryRepository
import com.example.trempel.R
import com.example.trempel.ui.RecyclerItem
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<RecyclerItem>>()
    val categories: LiveData<List<RecyclerItem>> get() = _categories
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    var isInProgressTemp = ObservableBoolean(true)

    fun loadProduct() {
        disposable = categoryRepository.getAllCategories()
            .doOnSubscribe {
                isInProgressTemp.set(true)
            }
            .doFinally {
                isInProgressTemp.set(false)
            }
            .subscribe({ response ->
                _categories.value = response.map { CategoriesItemViewModel(it) }
                    .map { it.toRecyclerItem() }
            }, { error ->
                _errorLiveData.value = error.message
            })
    }

    private fun CategoriesItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.category,
        layoutId = R.layout.category_item
    )
}
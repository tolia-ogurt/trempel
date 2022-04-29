package com.trempel.categories.ui.categories

import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.categories.R
import com.trempel.categories.repo.CategoryRepository
import com.trempel.core_ui.RecyclerItem
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<RecyclerItem>>()
    val categories: LiveData<List<RecyclerItem>> get() = _categories
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    private var disposable: Disposable? = null
    val isInProgressTemp = ObservableBoolean(true)

    fun loadProduct() {
        disposable = categoryRepository.getAllCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isInProgressTemp.set(true)
            }
            .doFinally {
                isInProgressTemp.set(false)
            }
            .subscribe(
                { response ->
                    _categories.value = response.map { CategoriesItemViewModel(it) }
                        .map { it.toRecyclerItem() }
                },
                { error ->
                    _errorLiveData.value = error as? TrempelException
                }
            )
    }

    private fun CategoriesItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.category,
        layoutId = R.layout.category_item
    )
}

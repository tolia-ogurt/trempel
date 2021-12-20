package com.example.trempel.ui.categories

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.CategoryRepository
import com.example.trempel.network.CategoryDomainModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
    ) :
    ViewModel() {

    private val _categories = MutableLiveData<List<CategoryDomainModel>>()
    val categories: LiveData<List<CategoryDomainModel>> get() = _categories
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    val isInProgressTemp = ObservableBoolean(true)

    fun loadProduct() {
        disposable = categoryRepository.getAllCategories()
            .doOnSubscribe {
                isInProgressTemp.set(true)
            }
            .doFinally {
                isInProgressTemp.set(false)
            }
            .subscribe({ response ->
                _categories.value = response
            }, {
                _errorLiveData.value = it.message
            })
    }
}
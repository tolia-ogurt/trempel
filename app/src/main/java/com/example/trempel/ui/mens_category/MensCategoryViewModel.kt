package com.example.trempel.ui.mens_category

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.MensCategoryRepository
import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.model.CategoryModelItem
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class MensCategoryViewModel @Inject constructor(
    private val serviceRepository: MensCategoryRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<CategoryModelItem>>()
    val items: LiveData<List<CategoryModelItem>> get() = _items
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    val isInProgressTemp = ObservableBoolean(true)

    fun loadProduct(category: CategoryDomainModel) {
        disposable = serviceRepository.getMensCategory(category)
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
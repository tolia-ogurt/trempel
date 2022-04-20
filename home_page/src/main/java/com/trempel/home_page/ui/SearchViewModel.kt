package com.trempel.home_page.ui

import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import com.example.home_page.R
import com.trempel.core_ui.RecyclerItem
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import com.trempel.home_page.repository.SearchRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<RecyclerItem>>()
    val items: LiveData<List<RecyclerItem>> get() = _items
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    val isInProgressTemp = ObservableBoolean(true)
    val noResultTextVisibility: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun loadSearchResult(keyWord: String) {
        viewModelScope.launch {
            runCatching {
                searchRepository.getProductsBySearchQuery(keyWord)
                    .map { SearchItemViewModel(it) }
                    .map { it.toRecyclerItem() }
            }.onFailure { error ->
                _errorLiveData.value = error as? TrempelException
            }.onSuccess { response ->
                _items.postValue(response)
            }
            isInProgressTemp.set(false)
        }
    }

    private fun SearchItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.searchResultItem,
        layoutId = R.layout.search_result_item
    )
}
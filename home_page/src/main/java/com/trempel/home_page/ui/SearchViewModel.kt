package com.trempel.home_page.ui

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import com.example.home_page.R
import com.trempel.core_ui.RecyclerItem
import com.trempel.home_page.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<RecyclerItem>>()
    val items: LiveData<List<RecyclerItem>> get() = _items
    val isInProgressTemp = ObservableBoolean(true)
    val noResultTextVisibility: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    init {
        Log.d(SearchViewModel::class.simpleName, "Hiiiiiii, init called!")
    }

    fun loadSearchResult(keyWord: String) {
        viewModelScope.launch {
            _items.postValue(
                searchRepository.getProductsBySearchQuery(keyWord)
                    .map { SearchItemViewModel(it) }
                    .map { it.toRecyclerItem() }
            )
            isInProgressTemp.set(false)
        }
    }

    private fun SearchItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.searchResultItem,
        layoutId = R.layout.search_result_item
    )
}
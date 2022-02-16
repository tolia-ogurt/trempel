package com.trempel.bag.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trempel.bag.R
import com.trempel.bag.repository.BagRepository
import com.trempel.core_network.BagModelItem
import com.trempel.core_ui.RecyclerItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class BagViewModel @Inject constructor(
    val bagRepository: BagRepository
) : ViewModel() {

    private val _bagItems = MutableLiveData<List<RecyclerItem>>()
    val bagItems: LiveData<List<RecyclerItem>> get() = _bagItems


    fun loadData() {
        viewModelScope.launch {
            val response = bagRepository.getAllCarts()
            _bagItems.value = response.body()?.map { BagItemViewModel(it) }?.map { it.toRecyclerItem() }
        }
    }

    private fun BagItemViewModel.toRecyclerItem() = RecyclerItem(
        data = this,
        variableId = BR.item,
        layoutId = R.layout.bag_item
    )
}
package com.trempel.bag.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.trempel.bag.model.BagDomainModel
import com.trempel.core_ui.RecyclerItemComparator

class BagItemViewModel(
    val item: BagDomainModel
) : ViewModel(), RecyclerItemComparator {

    private val _quantity = MutableLiveData(item.quantity)
    val quantity: LiveData<Int> get() = _quantity
    val deleteLiveData = MutableLiveData<Int>()

    fun onItemClicked(view: View, id: Int) {
        val action = BagFragmentDirections.actionBagFragmentToNavGraphPdp(id)
        view.findNavController().navigate(action)
    }

    fun deleteItem() {
        deleteLiveData.value = item.id
    }

    fun increaseQuantityItem() {
        _quantity.value = _quantity.value?.plus(1)
    }

    fun reduceQuantityItem() {
        if (quantity.value ?: 0 > 1)
            _quantity.value = _quantity.value?.minus(1)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as BagItemViewModel
        return this.item.id == other.item.id
    }

    override fun isSameContent(other: Any): Boolean {
        other as BagItemViewModel
        return this.item == other.item
    }
}
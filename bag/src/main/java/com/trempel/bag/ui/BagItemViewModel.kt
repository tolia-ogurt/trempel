package com.trempel.bag.ui

import androidx.lifecycle.ViewModel
import com.trempel.core_network.BagModelItem
import com.trempel.core_ui.RecyclerItemComparator

class BagItemViewModel(
    val item: BagModelItem
) : ViewModel(), RecyclerItemComparator {


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
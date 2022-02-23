package com.trempel.home_page.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.trempel.core_ui.RecyclerItemComparator
import com.trempel.home_page.model.SearchResultModel

class SearchItemViewModel(
    val product: SearchResultModel
) : ViewModel(), RecyclerItemComparator {

    fun onItemClicked(view: View, id: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToNavGraphPdp(id)
        view.findNavController().navigate(action)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as SearchItemViewModel
        return this.product.id == other.product.id
    }

    override fun isSameContent(other: Any): Boolean {
        other as SearchItemViewModel
        return this.product == other.product
    }
}
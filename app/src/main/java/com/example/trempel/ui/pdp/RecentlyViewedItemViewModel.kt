package com.example.trempel.ui.pdp

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.trempel.db.RecentlyViewed
import com.example.trempel.ui.RecyclerItemComparator

internal class RecentlyViewedItemViewModel(
    val product: RecentlyViewed
) : ViewModel(), RecyclerItemComparator {

    fun onItemClicked(view: View, id: Int) {
        val action = PdpFragmentDirections.actionPdpFragmentSelf(id)
        view.findNavController().navigate(action)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as RecentlyViewedItemViewModel
        return this.product.id == other.product.id
    }

    override fun isSameContent(other: Any): Boolean {
        other as RecentlyViewedItemViewModel
        return this.product == other.product
    }
}
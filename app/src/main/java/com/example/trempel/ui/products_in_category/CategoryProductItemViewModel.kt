package com.example.trempel.ui.products_in_category

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.trempel.network.model.CategoryModelItem
import com.example.trempel.ui.RecyclerItemComparator

internal class CategoryProductItemViewModel(
    val product: CategoryModelItem
) : ViewModel(), RecyclerItemComparator {

    fun onItemClicked(view: View, id: Int) {
        val action = CategoryProductsFragmentDirections.actionMensCategoryFragmentToPdpFragment(id)
        view.findNavController().navigate(action)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as CategoryProductItemViewModel
        return this.product.id == other.product.id
    }

    override fun isSameContent(other: Any): Boolean {
        other as CategoryProductItemViewModel
        return this.product == other.product
    }
}
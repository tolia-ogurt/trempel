package com.example.trempel.ui.products_in_category

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.trempel.network.model.CategoryModelItem

internal class CategoryProductItemViewModel(
    val product: CategoryModelItem
) : ViewModel() {

    fun onItemClicked(view: View, id: Int) {
        val action = CategoryProductsFragmentDirections.actionMensCategoryFragmentToPdpFragment(id)
        view.findNavController().navigate(action)
    }
}
package com.example.trempel.ui.categories

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.trempel.network.CategoryDomainModel

internal class CategoriesItemViewModel(
    val category: CategoryDomainModel
) : ViewModel() {

    fun onItemClicked(view: View, category: CategoryDomainModel) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToMensCategoryFragment(category)
        view.findNavController().navigate(action)
    }
}
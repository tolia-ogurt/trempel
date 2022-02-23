package com.trempel.categories.ui.categories

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.trempel.categories.model.CategoryDomainModel
import com.trempel.core_ui.RecyclerItemComparator

internal class CategoriesItemViewModel(
    val category: CategoryDomainModel
) : ViewModel(), RecyclerItemComparator {

    fun onItemClicked(view: View, category: CategoryDomainModel) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToCategoryProductsFragment(category)
        view.findNavController().navigate(action)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as CategoriesItemViewModel
        return this.category.name == other.category.name
    }

    override fun isSameContent(other: Any): Boolean {
        other as CategoriesItemViewModel
        return this.category == other.category
    }
}

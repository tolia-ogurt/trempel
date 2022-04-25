package com.trempel.categories.ui.products_in_category

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.trempel.categories.model.CategoryProduct
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.core_network.favorites_db.db.FavoritesDbRepository
import com.trempel.core_ui.RecyclerItemComparator
import kotlinx.coroutines.launch

internal class CategoryProductItemViewModel(
    val product: CategoryProduct,
    private val bagDbRepository: BagDbRepository,
    private val favoritesDbRepository: FavoritesDbRepository
) : ViewModel(), RecyclerItemComparator {

    val isFavorite = favoritesDbRepository.isFavorite(product.id)

    fun transferringItemFavorites(isChecked: Boolean) {
        if (isChecked == isFavorite.value) return
        if (isChecked) {
            viewModelScope.launch { favoritesDbRepository.addProductToFavorites(product.id) }
        } else {
            viewModelScope.launch { favoritesDbRepository.deleteProductFromDbFavoritesById(product.id) }
        }
    }

    fun onItemClicked(view: View, id: Int) {
        val action = CategoryProductsFragmentDirections.actionCategoryProductsToPdpFragment(id)
        view.findNavController().navigate(action)
    }

    fun addProductToBag() {
        viewModelScope.launch {
            bagDbRepository.addProductToBag(product.id)
        }
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
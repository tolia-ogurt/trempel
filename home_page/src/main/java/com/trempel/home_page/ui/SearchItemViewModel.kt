package com.trempel.home_page.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.trempel.categories.ui.products_in_category.CategoryProductsFragmentDirections
import com.trempel.core_network.bag_db.db.BagDbRepository
import com.trempel.core_network.favorites_db.db.FavoritesDbRepository
import com.trempel.core_ui.RecyclerItemComparator
import com.trempel.home_page.model.SearchResultModel
import kotlinx.coroutines.launch

class SearchItemViewModel(
    val product: SearchResultModel,
    private val favoritesDbRepository: FavoritesDbRepository,
    private val bagDbRepository: BagDbRepository
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

    fun addProductToBag() {
        viewModelScope.launch {
            bagDbRepository.addProductToBag(product.id)
        }
    }

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
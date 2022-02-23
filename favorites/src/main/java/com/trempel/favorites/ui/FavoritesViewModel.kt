package com.trempel.favorites.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favorites.BR
import com.example.favorites.R
import com.trempel.core_network.favorites_db.db.FavoritesDbRepository
import com.trempel.core_ui.RecyclerItem
import com.trempel.favorites.model.toFavoritesDomainModel
import com.trempel.favorites.model.toFavoritesEntity
import com.trempel.favorites.repository.FavoritesNetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesNetworkRepository: FavoritesNetworkRepository,
    private val favoritesDbRepository: FavoritesDbRepository
) : ViewModel(), DefaultLifecycleObserver {

    private val _favoritesItems = MutableLiveData<List<RecyclerItem>>()
    val favoritesItems: LiveData<List<RecyclerItem>> get() = _favoritesItems

    private val deleteObserver = Observer<Int> { productId ->
        viewModelScope.launch {
            favoritesDbRepository.deleteProductFromDbFavoritesById(productId)
            _favoritesItems.value = _favoritesItems.value
                ?.filter { (it.data as FavoriteItemViewModel).item.id != productId }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _favoritesItems.value = favoritesDbRepository.getProductsFromDbFavorites()
                .map { favoritesNetworkRepository.getProduct(it.productId) to it.quantity }
                .map { it.first.toFavoritesDomainModel(it.second) }
                .map { FavoriteItemViewModel(it) }
                .onEach {
                    it.deleteLiveData.observeForever(deleteObserver)
                }
                .map { it.toRecyclerItem() }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        loadData()
    }

    override fun onCleared() {
        _favoritesItems.value?.map { it.data as FavoriteItemViewModel }
            ?.forEach { it.deleteLiveData.removeObserver(deleteObserver) }
        super.onCleared()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        viewModelScope.launch {
            _favoritesItems.value?.let { bagItems ->
                favoritesDbRepository.updateQuantity(
                    *bagItems.map { it.data as FavoriteItemViewModel }
                        .map { it.item.toFavoritesEntity(it.quantity.value ?: 0) }.toTypedArray()
                )
            }
        }
    }

    private fun FavoriteItemViewModel.toRecyclerItem(): RecyclerItem {
        val recyclerItem = RecyclerItem(
            data = this,
            variableId = BR.item,
            layoutId = R.layout.favorite_item
        )
        return recyclerItem
    }
}

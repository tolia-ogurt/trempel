package com.example.trempel.ui.products_in_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trempel.databinding.CategoryProductsItemBinding
import com.example.trempel.network.model.CategoryModelItem

internal class CategoryProductsRecyclerAdapter :
    RecyclerView.Adapter<CategoryProductsRecyclerAdapter.CategoryProductsViewHolder>() {

    private var itemsViewModels: List<CategoryProductItemViewModel>? = null

    class CategoryProductsViewHolder(
        val binding: CategoryProductsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = CategoryProductsItemBinding.inflate(inflater, parent, false)
        return CategoryProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryProductsViewHolder, position: Int) {
        itemsViewModels?.get(position)?.let { holder.binding.viewModel = it }
    }

    override fun getItemCount() = itemsViewModels?.size ?: 0

    fun updateItems(items: List<CategoryModelItem>?) {
        itemsViewModels = items?.map { CategoryProductItemViewModel(it) }
        notifyDataSetChanged()
    }
}
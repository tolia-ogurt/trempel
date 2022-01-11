package com.example.trempel.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trempel.databinding.CategoryItemBinding
import com.example.trempel.network.CategoryDomainModel

internal class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoryList: List<CategoriesItemViewModel>? = null

    class CategoriesViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        categoryList?.get(position).let { holder.binding.category = it }

    }

    override fun getItemCount(): Int = categoryList?.size ?: 0

    fun updateItems(items: List<CategoryDomainModel>?) {
        categoryList = items?.map { CategoriesItemViewModel(it) }
        notifyDataSetChanged()
    }
}
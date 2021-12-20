package com.example.trempel.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trempel.databinding.CategoryItemBinding
import com.example.trempel.network.CategoryDomainModel

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var categoryList = listOf<CategoryDomainModel>()
    var onItemClicked: ((CategoryDomainModel) -> Unit)? = null

    class CategoriesViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.binding.category = categoryList[position]
        holder.binding.containerAllCategories.setOnClickListener {
            onItemClicked?.invoke(categoryList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}
package com.example.trempel.ui.mens_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trempel.databinding.MensCategoryItemBinding
import com.example.trempel.network.model.CategoryModelItem

internal class MensCategoryRecyclerAdapter :
    RecyclerView.Adapter<MensCategoryRecyclerAdapter.MensCategoryViewHolder>() {

    var itemList = listOf<CategoryModelItem>()
    var onItemClicked: ((Int) -> Unit)? = null

    class MensCategoryViewHolder(
        val binding: MensCategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = MensCategoryItemBinding.inflate(inflater, parent, false)
        return MensCategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MensCategoryViewHolder, position: Int) {
        holder.binding.item = itemList[position]
        holder.binding.container.setOnClickListener {
            onItemClicked?.invoke(itemList[position].id)
        }
    }

    override fun getItemCount() = itemList.size
}
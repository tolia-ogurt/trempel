package com.example.trempel.ui.pdp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trempel.databinding.RecentlyViewedItemBinding
import com.example.trempel.db.RecentlyViewed
import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.ui.categories.CategoriesItemViewModel

internal class RecentlyViewedAdapter :
    ListAdapter<RecentlyViewed, RecentlyViewedAdapter.RecentlyViewHolder>(RecentlyViewedComparator()) {

    var itemList = listOf<RecentlyViewed>()
    var onItemClicked: ((Int) -> Unit)? = null

    class RecentlyViewHolder(
        val binding: RecentlyViewedItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val iteView = RecentlyViewedItemBinding.inflate(layoutInflater, parent, false)
        return RecentlyViewHolder(iteView)
    }

    override fun onBindViewHolder(holder: RecentlyViewHolder, position: Int) {
        holder.binding.recentlyView = itemList[position]
        holder.binding.containerRecentlyViewItem.setOnClickListener {
            onItemClicked?.invoke(itemList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

internal class RecentlyViewedComparator : DiffUtil.ItemCallback<RecentlyViewed>() {
    override fun areItemsTheSame(oldItem: RecentlyViewed, newItem: RecentlyViewed): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecentlyViewed, newItem: RecentlyViewed): Boolean {
        return oldItem.id == newItem.id
    }
}

package com.example.trempel

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trempel.network.CategoryDomainModel
import com.example.trempel.network.model.CategoryModelItem
import com.example.trempel.ui.categories.CategoriesAdapter
import com.example.trempel.ui.products_in_category.CategoryProductsRecyclerAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("app:boldPart")
fun TextView.setBoldPart(textPart: String) {
    val startIndex = text.indexOf(textPart).takeUnless { it == -1 } ?: return
    this.text = SpannableString(text).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex,
            textPart.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

@BindingAdapter("app:boldPart")
fun TextView.setBoldPart(textPart: Float) {
    this.setBoldPart(textPart.toString())
}

@BindingAdapter("app:dynamicMaxLines", "android:lineSpacingExtra")
fun TextView.setMaxLines(dynamic: Boolean, extraSpacing: Float) {
    setLineSpacing(extraSpacing, 1f)
    if (!dynamic) return
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        maxLines = (measuredHeight / (textSize + extraSpacing)).toInt()
    }
}

@BindingAdapter("app:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.scaleX = if (isVisible) 1f else 0f
}

@BindingAdapter("itemViewModels")
internal fun bindItemViewModels(recyclerView: RecyclerView, itemList: List<CategoryModelItem>?) {
    val adapter = getOrCreateProductsAdapter(recyclerView)
    adapter.updateItems(itemList)
}

private fun getOrCreateProductsAdapter(recyclerView: RecyclerView): CategoryProductsRecyclerAdapter {
        val productsInCategoryRecyclerAdapter = CategoryProductsRecyclerAdapter()
        recyclerView.adapter = productsInCategoryRecyclerAdapter
        return productsInCategoryRecyclerAdapter
}

@BindingAdapter("itemViewModels")
internal fun bindCategoryItemViewModels(recyclerView: RecyclerView, itemList: List<CategoryDomainModel>?) {
    val adapter = getOrCreateCategoriesAdapter(recyclerView)
    adapter.updateItems(itemList)
}

private fun getOrCreateCategoriesAdapter(recyclerView: RecyclerView): CategoriesAdapter {
    val categoriesAdapter = CategoriesAdapter()
    recyclerView.adapter = categoriesAdapter
    return categoriesAdapter
}
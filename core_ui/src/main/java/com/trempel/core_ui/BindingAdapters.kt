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
import com.trempel.core_ui.DataBindingRecyclerAdapter
import com.trempel.core_ui.RecyclerItem
import com.trempel.core_ui.SwipeItemForDelete

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

@BindingAdapter("app:dynamicMaxLines")
fun TextView.setMaxLines(dynamic: Boolean) {
    if (!dynamic) return
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        maxLines = (measuredHeight / (textSize + lineSpacingExtra)).toInt()
    }
}

@BindingAdapter("app:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.scaleX = if (isVisible) 1f else 0f
}

@BindingAdapter("items")
internal fun setRecyclerViewItems(recyclerView: RecyclerView, items: List<RecyclerItem>?) {
    var adapter = (recyclerView.adapter as? DataBindingRecyclerAdapter)
    if (adapter == null) {
        adapter = DataBindingRecyclerAdapter()
        recyclerView.adapter = adapter
    }
    adapter.submitList(items.orEmpty())
}


@BindingAdapter("app:activeBtn")
fun View.activeBtn(quantity: Int) {
    this.isEnabled = quantity > 1
}

@BindingAdapter("swipe")
fun View.swipe(isSwipe:Boolean) {
    setOnTouchListener(SwipeItemForDelete())
}
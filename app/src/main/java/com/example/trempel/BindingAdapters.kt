package com.example.trempel

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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
        maxLines = measuredHeight / lineHeight
    }
}

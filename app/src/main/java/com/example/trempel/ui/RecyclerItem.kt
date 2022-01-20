package com.example.trempel.ui

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

internal data class RecyclerItem(
    val data: Any,
    @LayoutRes val layoutId: Int,
    val variableId: Int
) {
    fun bind(binding: ViewDataBinding) {
        binding.setVariable(variableId, data)
    }
}

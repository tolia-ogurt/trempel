package com.trempel.core_ui

interface RecyclerItemComparator {
    fun isSameItem(other: Any): Boolean
    fun isSameContent(other: Any): Boolean
}

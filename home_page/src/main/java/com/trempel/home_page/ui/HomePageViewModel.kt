package com.trempel.home_page.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class HomePageViewModel @Inject constructor() : ViewModel() {

    val searchQueryData = MutableLiveData<String>()
    private val _onSearch = MutableLiveData<String>()
    val onSearch: LiveData<String> get() = _onSearch
    private val _toast = MutableLiveData<Unit>()
    val toast: LiveData<Unit> get() = _toast

    fun submitText() {
        val word = searchQueryData.value.orEmpty().trim()
        if (word.length > MIN_LENGTH_KEY_WORD) {
            _onSearch.value = word
        } else {
            _toast.value = Unit
        }
    }

    companion object {
        const val MIN_LENGTH_KEY_WORD = 3
    }
}
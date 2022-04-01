package com.trempel.home_page.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomePageViewModel @Inject constructor() : ViewModel() {

    val searchQueryData = MutableLiveData<String>()
    private val _submitOnSearch = MutableLiveData("")
    val submitOnSearch: LiveData<String> get() = _submitOnSearch
    private val _toast = MutableLiveData<Unit>()
    val toast: LiveData<Unit> get() = _toast

    fun submitText() {
        val word = searchQueryData.value.orEmpty().trim()
        if (word.length > MIN_LENGTH_KEY_WORD) {
            _submitOnSearch.value = word
        } else {
            _toast.value = Unit
        }
    }

    private companion object {
        const val MIN_LENGTH_KEY_WORD = 3
    }
}
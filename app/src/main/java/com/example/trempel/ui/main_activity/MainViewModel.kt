package com.example.trempel.ui.main_activity

import androidx.lifecycle.ViewModel
import com.example.trempel.SessionManager
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val sessionManager: SessionManager
) : ViewModel() {

    val isLoggedIn: Boolean
        get() = !sessionManager.fetchAuthToken().isNullOrBlank()
}
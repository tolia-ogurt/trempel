package com.example.trempel.ui.main_activity

import androidx.lifecycle.ViewModel
import com.example.login.repo.SessionManager
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    val sessionManager: com.example.login.repo.SessionManager
) : ViewModel() {

    val isLoggedIn: Boolean
        get() = !sessionManager.authToken.isNullOrBlank()
}
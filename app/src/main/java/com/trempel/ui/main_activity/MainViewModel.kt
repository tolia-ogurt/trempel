package com.trempel.ui.main_activity

import androidx.lifecycle.ViewModel
import com.trempel.login.repo.SessionManager
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    val sessionManager: SessionManager
) : ViewModel() {

    val isLoggedIn: Boolean
        get() = !sessionManager.authToken.isNullOrBlank()
}
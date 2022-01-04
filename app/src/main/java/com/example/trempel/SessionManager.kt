package com.example.trempel

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    fun saveAuth(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }
}
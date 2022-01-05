package com.example.trempel

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

internal class SessionManager @Inject constructor(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )
    var authToken: String?
        get() = prefs.getString(USER_TOKEN, null)
        set(token) {
            val editor = prefs.edit()
            editor.putString(USER_TOKEN, token)
            editor.apply()
        }

    private companion object {
        const val USER_TOKEN = "user_token"
    }
}
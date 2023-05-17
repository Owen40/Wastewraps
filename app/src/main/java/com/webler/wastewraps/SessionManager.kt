package com.webler.wastewraps

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth

object SessionManager {
    private const val PREF_NAME = "Session"
    private const val KEY_SESSION_TOKEN = "sessionToken"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context : Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
    fun getSessionToken(): String? {
        return sharedPreferences.getString(KEY_SESSION_TOKEN, null)
    }

    fun setSessionToken(token: String?) {
        sharedPreferences.edit().putString(KEY_SESSION_TOKEN, token).apply()
    }

    fun clearSession() {
        FirebaseAuth.getInstance().signOut()
        sharedPreferences.edit().remove(KEY_SESSION_TOKEN).apply()
    }

    fun isLoggedIn(): Boolean {
        return getSessionToken() != null
    }
}
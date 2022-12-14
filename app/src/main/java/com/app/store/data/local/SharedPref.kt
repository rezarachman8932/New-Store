package com.app.store.data.local

import android.content.Context
import com.app.store.shared.constant.SharedPrefConstant

class SharedPref(context: Context) {

    private val sessionPref = context.getSharedPreferences(SharedPrefConstant.PREF_SESSION, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        sessionPref.edit().putString(SharedPrefConstant.PREF_TOKEN, token).apply()
    }

    fun deleteToken() {
        sessionPref.edit().remove(SharedPrefConstant.PREF_TOKEN).apply()
    }

    fun getToken(): String {
        return sessionPref.getString(SharedPrefConstant.PREF_TOKEN, "").orEmpty()
    }

    fun setLoggedIn(loggedIn: Boolean) {
        sessionPref.edit().putBoolean(SharedPrefConstant.IS_LOGGED_IN, loggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return sessionPref.getBoolean(SharedPrefConstant.IS_LOGGED_IN, false)
    }

    fun setName(name: String) {
        sessionPref.edit().putString(SharedPrefConstant.PREF_NAME, name).apply()
    }

    fun getName(): String {
        return sessionPref.getString(SharedPrefConstant.PREF_NAME, "").orEmpty()
    }

    fun deleteName() {
        sessionPref.edit().remove(SharedPrefConstant.PREF_NAME).apply()
    }

}
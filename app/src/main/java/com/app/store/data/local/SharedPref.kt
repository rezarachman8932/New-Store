package com.app.store.data.local

import android.content.Context
import com.app.store.shared.constant.SharedPrefConstant

class SharedPref(context: Context) {

    private val sessionPref = context.getSharedPreferences(SharedPrefConstant.PREF_SESSION, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        sessionPref.edit().putString(SharedPrefConstant.PREF_TOKEN, token).apply()
    }

    fun getToken(): String {
        return sessionPref.getString(SharedPrefConstant.PREF_TOKEN, "").orEmpty()
    }

}
package com.app.store.data.repository

import com.app.store.data.domain.UserService
import com.app.store.data.local.SharedPref
import com.app.store.data.model.UserCreateRequest
import com.app.store.shared.constant.SharedPrefConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userService: UserService,
    private val sharedPref: SharedPref
) {

    suspend fun createUser(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userService.createUser(request)
    }

    suspend fun createSession(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userService.createSession(request)
    }

    suspend fun destroySession() = withContext(Dispatchers.IO) {
        userService.destroySession()
    }

    suspend fun setToken(token: String) = withContext(Dispatchers.IO) {
        sharedPref.setToken(token)
    }

    suspend fun deleteToken() = withContext(Dispatchers.IO) {
        sharedPref.deleteToken()
    }

    fun setLoggedIn(loggedIn: Boolean) {
        sharedPref.setLoggedIn(loggedIn)
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.isLoggedIn()
    }

}
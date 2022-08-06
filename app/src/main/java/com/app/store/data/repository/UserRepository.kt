package com.app.store.data.repository

import com.app.store.data.domain.UserService
import com.app.store.data.model.UserCreateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userService: UserService) {

    suspend fun createUser(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userService.createUser(request)
    }

    suspend fun createSession(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userService.createSession(request)
    }

    suspend fun destroySession() = withContext(Dispatchers.IO) {
        userService.destroySession()
    }

}
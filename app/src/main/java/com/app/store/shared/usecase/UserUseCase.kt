package com.app.store.shared.usecase

import com.app.store.data.model.UserCreateRequest
import com.app.store.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun createUser(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userRepository.createUser(request)
    }

    suspend fun createSession(request: UserCreateRequest) = withContext(Dispatchers.IO) {
        userRepository.createSession(request)
    }

    suspend fun destroySession() = withContext(Dispatchers.IO) {
        userRepository.destroySession()
    }

    suspend fun setToken(token: String) = withContext(Dispatchers.IO) {
        userRepository.setToken(token)
    }

    suspend fun deleteToken() = withContext(Dispatchers.IO) {
        userRepository.deleteToken()
    }

    fun setLoggedIn(loggedIn: Boolean) {
        userRepository.setLoggedIn(loggedIn)
    }

    fun isLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }

    fun setName(name: String) {
        userRepository.setName(name)
    }

    fun getName(): String {
        return userRepository.getName()
    }

    fun deleteName() {
        userRepository.deleteName()
    }

}
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

}
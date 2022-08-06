package com.app.store.data.domain

import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import com.app.store.data.model.UserDeleteSessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserService {

    @POST("users")
    suspend fun createUser(
        @Body request: UserCreateRequest
    ) : Response<UserCreateSession>

    @POST("session")
    suspend fun createSession(
        @Body request: UserCreateRequest
    ) : Response<UserCreateSession>

    @DELETE("session")
    suspend fun destroySession() : Response<UserDeleteSessionResponse>

}
package com.app.store.data.domain

import com.app.store.data.model.UserCreateRequest
import com.app.store.data.model.UserCreateSession
import com.app.store.data.model.UserDeleteSessionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface UserService {

    @POST("api/users")
    suspend fun createUser(
        @Body request: UserCreateRequest
    ) : Response<UserCreateSession>

    @POST("api/session")
    suspend fun createSession(
        @Body request: UserCreateRequest
    ) : Response<UserCreateSession>

    @DELETE("api/session")
    suspend fun destroySession() : Response<UserDeleteSessionResponse>

}
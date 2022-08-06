package com.app.store.data.model

import com.squareup.moshi.Json

data class UserCreateSession(
    @Json(name = "User-Token") val userToken: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "login") val login: String? = null,
    @Json(name = "password") val password: String? = null
)

data class UserCreateRequest(
    @Json(name = "user") val user: UserCreateSession
)

data class UserDeleteSessionResponse(
    @Json(name = "message") val message: String
)
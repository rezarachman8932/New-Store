package com.app.store.shared.model

import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "data") val data: T
)
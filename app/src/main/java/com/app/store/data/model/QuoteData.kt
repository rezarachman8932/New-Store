package com.app.store.data.model

import com.squareup.moshi.Json

data class QuoteOfTheDayResponse(
    @Json(name = "qotd_date") val qotd_date: String,
    @Json(name = "quote") val quote: QuoteOfTheDayDetail
)

data class QuoteListResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "last_page") val last_page: Boolean,
    @Json(name = "quotes") val quotes: MutableList<QuoteOfTheDayDetail>
)

data class QuoteOfTheDayDetail(
    @Json(name = "id") val id: Int,
    @Json(name = "favorites_count") val favorites_count: Int,
    @Json(name = "dialogue") val dialogue: Boolean,
    @Json(name = "favorite") val favorite: Boolean,
    @Json(name = "tags") val tags: MutableList<String>,
    @Json(name = "url") val url: String,
    @Json(name = "upvotes_count") val upvotes_count: Int,
    @Json(name = "downvotes_count") val downvotes_count: Int,
    @Json(name = "author") val author: String,
    @Json(name = "author_permalink") val author_permalink: String,
    @Json(name = "body") val body: String
)
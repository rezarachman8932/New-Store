package com.app.store.data.domain

import com.app.store.data.model.QuoteListResponse
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.data.model.QuoteOfTheDayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuoteService {

    @GET("qotd")
    suspend fun getQuoteOfTheDay() : Response<QuoteOfTheDayResponse>

    @GET("quotes")
    suspend fun getListQuotes() : Response<QuoteListResponse>

    @GET("quotes")
    suspend fun getListQuotesBySearch(
        @Query("filter") filter: String
    ) : Response<QuoteListResponse>

    @GET("quotes")
    suspend fun getListQuotesByTag(
        @Query("filter") filter: String,
        @Query("type") type: String
    ) : Response<QuoteListResponse>

    @GET("quotes/{quote_id}")
    suspend fun getQuote(
        @Path("quote_id") quoteId: String
    ) : Response<QuoteOfTheDayDetail>

}
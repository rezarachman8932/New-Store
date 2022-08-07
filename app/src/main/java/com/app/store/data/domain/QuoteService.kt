package com.app.store.data.domain

import com.app.store.data.model.QuoteListResponse
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.data.model.QuoteOfTheDayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteService {

    @GET("qotd")
    suspend fun getQuoteOfTheDay() : Response<QuoteOfTheDayResponse>

    @GET("quotes")
    suspend fun getListQuotes() : Response<QuoteListResponse>

    @GET("quotes/{quote_id}")
    suspend fun getQuote(
        @Path("quote_id") quoteId: String
    ) : Response<QuoteOfTheDayDetail>

}
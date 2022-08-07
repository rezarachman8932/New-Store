package com.app.store.data.domain

import com.app.store.data.model.QuoteListResponse
import com.app.store.data.model.QuoteOfTheDayDetail
import com.app.store.data.model.QuoteOfTheDayResponse
import com.app.store.shared.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteService {

    @GET("api/qotd")
    suspend fun getQuoteOfTheDay() : Response<BaseResponse<QuoteOfTheDayResponse>>

    @GET("api/quotes")
    suspend fun getListQuotes() : Response<BaseResponse<QuoteListResponse>>

    @GET("api/quotes/{quote_id}")
    suspend fun getQuote(
        @Path("quote_id") quoteId: String
    ) : Response<BaseResponse<QuoteOfTheDayDetail>>

}
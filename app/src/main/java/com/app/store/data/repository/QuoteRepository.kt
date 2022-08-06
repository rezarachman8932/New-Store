package com.app.store.data.repository

import com.app.store.data.domain.QuoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val quoteService: QuoteService) {

    suspend fun getQuoteOfTheDay() = withContext(Dispatchers.IO) {
        quoteService.getQuoteOfTheDay()
    }

    suspend fun getListQuotes() = withContext(Dispatchers.IO) {
        quoteService.getListQuotes()
    }

    suspend fun getQuote(quoteId: String) = withContext(Dispatchers.IO) {
        quoteService.getQuote(quoteId)
    }

}
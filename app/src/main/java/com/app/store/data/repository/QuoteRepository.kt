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

    suspend fun getListQuotesBySearch(keyword: String) = withContext(Dispatchers.IO) {
        quoteService.getListQuotesBySearch(keyword)
    }

    suspend fun getListQuotesByTag(tag: String) = withContext(Dispatchers.IO) {
        quoteService.getListQuotesByTag(tag, "tag")
    }

    suspend fun getQuote(quoteId: String) = withContext(Dispatchers.IO) {
        quoteService.getQuote(quoteId)
    }

}
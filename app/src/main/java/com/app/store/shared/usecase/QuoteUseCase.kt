package com.app.store.shared.usecase

import com.app.store.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteUseCase(private val quoteRepository: QuoteRepository) {

    suspend fun getQuoteOfTheDay() = withContext(Dispatchers.IO) {
        quoteRepository.getQuoteOfTheDay()
    }

    suspend fun getListQuotes() = withContext(Dispatchers.IO) {
        quoteRepository.getListQuotes()
    }

    suspend fun getListQuotesBySearch(keyword: String) = withContext(Dispatchers.IO) {
        quoteRepository.getListQuotesBySearch(keyword)
    }

    suspend fun getListQuotesByTag(tag: String) = withContext(Dispatchers.IO) {
        quoteRepository.getListQuotesByTag(tag)
    }

    suspend fun getQuote(quoteId: String) = withContext(Dispatchers.IO) {
        quoteRepository.getQuote(quoteId)
    }

}
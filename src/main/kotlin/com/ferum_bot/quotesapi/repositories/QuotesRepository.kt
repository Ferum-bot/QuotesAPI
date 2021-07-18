package com.ferum_bot.quotesapi.repositories

import com.ferum_bot.quotesapi.models.dto.QuoteDTO

interface QuotesRepository: BaseApiRepository {

    fun getAllQuotes(): Collection<QuoteDTO>

    fun getRandomQuotes(count: Int): Collection<QuoteDTO>

    fun getQuotesFrom(start: Int, count: Int): Collection<QuoteDTO>

    fun getQuotesWithTag(tag: String): Collection<QuoteDTO>

    fun getQuotesWithAuthor(author: String): Collection<QuoteDTO>
}
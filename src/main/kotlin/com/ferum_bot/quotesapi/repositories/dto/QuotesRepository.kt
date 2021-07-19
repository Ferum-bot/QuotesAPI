package com.ferum_bot.quotesapi.repositories.dto

import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.repositories.dto.BaseApiRepository

interface QuotesRepository: BaseApiRepository {

    fun getAllQuotes(): Collection<QuoteDTO>

    fun getRandomQuotes(count: Int): Collection<QuoteDTO>

    fun getQuotesFrom(page: Int, size: Int): Collection<QuoteDTO>

    fun getQuotesWithTag(tag: String): Collection<QuoteDTO>

    fun getQuotesWithAuthor(author: String): Collection<QuoteDTO>
}
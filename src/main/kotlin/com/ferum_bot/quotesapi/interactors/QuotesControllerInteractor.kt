package com.ferum_bot.quotesapi.interactors

import com.ferum_bot.quotesapi.models.MainScreenModel
import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse

interface QuotesControllerInteractor {

    fun getMainScreenModel(quotesCount: Int): QuotesResponse<MainScreenModel>

    fun getRandomQuotes(quotesCount: Int): QuotesResponse<Collection<QuoteDTO>>

    fun getAllQuotes(): QuotesResponse<Collection<QuoteDTO>>

    fun getQuotesFrom(startOffset: Int, count: Int): QuotesResponse<Collection<QuoteDTO>>

    fun getQuotesWithTag(tag: String): QuotesResponse<Collection<QuoteDTO>>

    fun getQuotesFromAuthor(author: String): QuotesResponse<Collection<QuoteDTO>>
}
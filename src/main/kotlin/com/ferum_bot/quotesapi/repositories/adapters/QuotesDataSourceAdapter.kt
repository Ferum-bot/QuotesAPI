package com.ferum_bot.quotesapi.repositories.adapters

import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.models.entity.QuoteEntity

interface QuotesDataSourceAdapter {

    fun adaptQuote(quote: QuoteEntity): QuoteDTO

    fun adaptQuotes(quotes: Collection<QuoteEntity>): Collection<QuoteDTO>

}
package com.ferum_bot.quotesapi.repositories.adapters.impl

import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.repositories.adapters.QuotesDataSourceAdapter

class QuotesRepositoryAdapter: QuotesDataSourceAdapter {

    override fun adaptQuote(quote: QuoteEntity): QuoteDTO {
        val id = quote.id!!
        val text = quote.text
        val authorFullName = quote.author?.authorFullName
        val tag = quote.tag?.tagName

        return QuoteDTO(
            id, text, authorFullName, tag
        )
    }

    override fun adaptQuotes(quotes: Collection<QuoteEntity>): Collection<QuoteDTO> {
        return quotes.map { adaptQuote(it) }
    }

}
package com.ferum_bot.quotesapi.interactors.impl

import com.ferum_bot.quotesapi.interactors.QuotesControllerInteractor
import com.ferum_bot.quotesapi.models.MainScreenModel
import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.repositories.QuotesRepository

class QuotesControllerInteractorImpl(
    private val repository: QuotesRepository
): QuotesControllerInteractor {

    private val availableTagsCount: Int
    get() = repository.getNumberOfAvailableTags()

    private val availableAuthorsCount: Int
    get() = repository.getNumberOfAvailableAuthors()

    private val availableQuotesCount: Int
    get() = repository.getNumberOfAvailableQuotes()

    override fun getMainScreenModel(quotesCount: Int): QuotesResponse<MainScreenModel> {
        val randomQuotes = repository.getRandomQuotes(quotesCount)
        val model = MainScreenModel(
            numberOfAvailableAuthors = availableAuthorsCount,
            numberOfAvailableTags = availableTagsCount,
            randomQuotes = randomQuotes,
        )

        return QuotesResponse(
            error = null,
            data = model,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getRandomQuotes(quotesCount: Int): QuotesResponse<Collection<QuoteDTO>> {
        val randomQuotes = repository.getRandomQuotes(quotesCount)

        return QuotesResponse(
            error = null,
            data = randomQuotes,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getAllQuotes(): QuotesResponse<Collection<QuoteDTO>> {
        val allQuotes = repository.getAllQuotes()

        return QuotesResponse(
            error = null,
            data = allQuotes,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getQuotesFrom(startOffset: Int, count: Int): QuotesResponse<Collection<QuoteDTO>> {
        val quotes = repository.getQuotesFrom(startOffset, count)

        return QuotesResponse(
            error = null,
            data = quotes,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getQuotesWithTag(tag: String): QuotesResponse<Collection<QuoteDTO>> {
        val quotes = repository.getQuotesWithTag(tag)

        return QuotesResponse(
            error = null,
            data = quotes,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getQuotesFromAuthor(author: String): QuotesResponse<Collection<QuoteDTO>> {
        val quotes = repository.getQuotesWithAuthor(author)

        return QuotesResponse(
            error = null,
            data = quotes,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }
}
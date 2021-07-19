package com.ferum_bot.quotesapi.interactors.impl

import com.ferum_bot.quotesapi.interactors.AuthorsControllerInteractor
import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.repositories.dto.AuthorsRepository

class AuthorsControllerInteractorImpl(
    private val repository: AuthorsRepository
): AuthorsControllerInteractor {

    private val availableAuthorsCount: Int
    get() = repository.getNumberOfAvailableAuthors()

    private val availableTagsCount: Int
    get() = repository.getNumberOfAvailableTags()

    private val availableQuotesCount: Int
    get() = repository.getNumberOfAvailableQuotes()

    override fun getAllAuthors(): QuotesResponse<Collection<AuthorDTO>> {
        val authors = repository.getAllAuthors()

        return QuotesResponse(
            error = null,
            data = authors,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun getAllAuthorsFrom(page: Int, size: Int): QuotesResponse<Collection<AuthorDTO>> {
        val authors = repository.getAuthorsFrom(page, size)

        return QuotesResponse(
            error = null,
            data = authors,
            numberOfQuotes = availableQuotesCount,
            numberOfTags = availableTagsCount,
            numberOfAuthors = availableAuthorsCount,
        )
    }

    override fun searchAuthors(name: String, page: Int, size: Int): QuotesResponse<Collection<AuthorDTO>> {
        val authors = repository.searchAuthors(name, page, size)

        return QuotesResponse(
            error = null,
            data = authors,
            numberOfAuthors = availableAuthorsCount,
            numberOfTags = availableTagsCount,
            numberOfQuotes = availableQuotesCount,
        )
    }
}
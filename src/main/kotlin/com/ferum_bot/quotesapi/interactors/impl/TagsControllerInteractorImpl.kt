package com.ferum_bot.quotesapi.interactors.impl

import com.ferum_bot.quotesapi.interactors.TagsControllerInteractor
import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.repositories.dto.TagsRepository

class TagsControllerInteractorImpl(
    private val repository: TagsRepository
): TagsControllerInteractor {

    private val availableTagsCount: Int
    get() = repository.getNumberOfAvailableTags()

    private val availableAuthorsCount: Int
    get() = repository.getNumberOfAvailableAuthors()

    private val availableQuotesCount: Int
    get() = repository.getNumberOfAvailableQuotes()

    override fun getAllTags(): QuotesResponse<Collection<TagDTO>> {
        val tags = repository.getAllTags()

        return QuotesResponse(
            error = null,
            data = tags,
            numberOfAuthors = availableAuthorsCount,
            numberOfTags = availableTagsCount,
            numberOfQuotes = availableQuotesCount,
        )
    }

}
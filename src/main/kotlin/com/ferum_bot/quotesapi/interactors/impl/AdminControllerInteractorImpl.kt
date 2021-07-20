package com.ferum_bot.quotesapi.interactors.impl

import com.ferum_bot.quotesapi.handlers.errors.CreateEntityErrorHandler
import com.ferum_bot.quotesapi.interactors.AdminControllerInteractor
import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.repositories.dto.AdminRepository

class AdminControllerInteractorImpl(
    private val repository: AdminRepository,
    private val errorHandler: CreateEntityErrorHandler,
): AdminControllerInteractor {

    override fun createNewAuthor(author: CreateAuthorModel): QuotesResponse<AuthorEntity> {
        val authorExists = repository.checkThatAuthorExists(author)
        if (authorExists) {
            return errorHandler.authorAlreadyExists(author)
        }

        val entity = repository.createNewAuthor(author)
        return QuotesResponse(
            error = null,
            data = entity,
        )
    }

    override fun createNewTag(tag: CreateTagModel): QuotesResponse<TagEntity> {
        val tagExists = repository.checkThatTagExists(tag)
        if (tagExists) {
            return errorHandler.tagAlreadyExists(tag)
        }

        val entity = repository.createNewTag(tag)
        return QuotesResponse(
            error = null,
            data = entity,
        )
    }

    override fun createNewQuote(quote: CreateQuoteModel): QuotesResponse<QuoteEntity> {
        val quoteExists = repository.checkThatQuoteExists(quote)
        val tagExists = repository.checkThatTagExists(quote.tagId)
        val authorExists = repository.checkThatAuthorExists(quote.authorId)
        if (quoteExists) {
            return errorHandler.quoteAlreadyExists(quote)
        }
        if (!tagExists) {
            return errorHandler.invalidTagId(quote.tagId)
        }
        if (!authorExists) {
            return errorHandler.invalidAuthorId(quote.authorId)
        }

        val entity = repository.createNewQuote(quote)
        return QuotesResponse(
            error = null,
            data = entity,
        )
    }

}
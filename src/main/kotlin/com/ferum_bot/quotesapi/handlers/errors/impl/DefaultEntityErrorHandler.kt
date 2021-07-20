package com.ferum_bot.quotesapi.handlers.errors.impl

import com.ferum_bot.quotesapi.handlers.errors.CreateEntityErrorHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.models.response.ErrorResponse
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.springframework.http.HttpStatus

class DefaultEntityErrorHandler: CreateEntityErrorHandler {

    override fun authorAlreadyExists(author: CreateAuthorModel): QuotesResponse<AuthorEntity> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "This author already exists: $author",
            errors = emptyList(),
        )
        return QuotesResponse(
            error = errorResponse,
            data = null,
        )
    }

    override fun tagAlreadyExists(tag: CreateTagModel): QuotesResponse<TagEntity> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "This tag already exists: $tag",
            errors = emptyList(),
        )
        return QuotesResponse(
            error = errorResponse,
            data = null,
        )
    }

    override fun quoteAlreadyExists(quote: CreateQuoteModel): QuotesResponse<QuoteEntity> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "This quote already exists: $quote",
            errors = emptyList(),
        )
        return QuotesResponse(
            error = errorResponse,
            data = null,
        )
    }

    override fun invalidAuthorId(authorId: Long): QuotesResponse<QuoteEntity> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "Can't find author with this id: $authorId",
            errors = emptyList(),
        )
        return QuotesResponse(
            error = errorResponse,
            data = null,
        )
    }

    override fun invalidTagId(tagId: Long): QuotesResponse<QuoteEntity> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "Can't find tag with this id: $tagId",
            errors = emptyList(),
        )
        return QuotesResponse(
            error = errorResponse,
            data = null,
        )
    }
}
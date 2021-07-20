package com.ferum_bot.quotesapi.handlers.errors

import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.springframework.http.ResponseEntity

interface CreateEntityErrorHandler {

    fun tagAlreadyExists(tag: CreateTagModel): QuotesResponse<TagEntity>

    fun authorAlreadyExists(author: CreateAuthorModel): QuotesResponse<AuthorEntity>

    fun quoteAlreadyExists(quote: CreateQuoteModel): QuotesResponse<QuoteEntity>

    fun invalidTagId(tagId: Long): QuotesResponse<QuoteEntity>

    fun invalidAuthorId(authorId: Long): QuotesResponse<QuoteEntity>
}
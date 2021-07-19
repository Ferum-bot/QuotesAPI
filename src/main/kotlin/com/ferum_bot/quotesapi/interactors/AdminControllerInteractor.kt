package com.ferum_bot.quotesapi.interactors

import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.models.response.QuotesResponse

interface AdminControllerInteractor {

    fun createNewTag(tag: CreateTagModel): QuotesResponse<TagEntity>

    fun createNewAuthor(author: CreateAuthorModel): QuotesResponse<AuthorEntity>

    fun createNewQuote(quote: CreateQuoteModel): QuotesResponse<QuoteEntity>

}
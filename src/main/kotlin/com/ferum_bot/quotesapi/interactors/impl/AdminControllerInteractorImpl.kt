package com.ferum_bot.quotesapi.interactors.impl

import com.ferum_bot.quotesapi.interactors.AdminControllerInteractor
import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.models.response.QuotesResponse

class AdminControllerInteractorImpl: AdminControllerInteractor {

    override fun createNewAuthor(author: CreateAuthorModel): QuotesResponse<AuthorEntity> {
        TODO("Not yet implemented")
    }

    override fun createNewQuote(quote: CreateQuoteModel): QuotesResponse<QuoteEntity> {
        TODO("Not yet implemented")
    }

    override fun createNewTag(tag: CreateTagModel): QuotesResponse<TagEntity> {
        TODO("Not yet implemented")
    }

}
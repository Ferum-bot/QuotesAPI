package com.ferum_bot.quotesapi.repositories.dto

import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity

interface AdminRepository {

    fun checkThatAuthorExists(author: CreateAuthorModel): Boolean

    fun checkThatAuthorExists(authorId: Long): Boolean

    fun createNewAuthor(author: CreateAuthorModel): AuthorEntity

    fun checkThatTagExists(tag: CreateTagModel): Boolean

    fun checkThatTagExists(tagId: Long): Boolean

    fun createNewTag(tag: CreateTagModel): TagEntity

    fun checkThatQuoteExists(quote: CreateQuoteModel): Boolean

    fun createNewQuote(quote: CreateQuoteModel): QuoteEntity
}
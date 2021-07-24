package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.create_entities.CreateAuthorModel
import com.ferum_bot.quotesapi.models.create_entities.CreateQuoteModel
import com.ferum_bot.quotesapi.models.create_entities.CreateTagModel
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.repositories.dto.AdminRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource

class DefaultAdminRepository(
    private val quotesDataSource: QuotesDataSource,
    private val tagsDataSource: TagsDataSource,
    private val authorsDataSource: AuthorsDataSource,
): AdminRepository {

    override fun checkThatAuthorExists(author: CreateAuthorModel): Boolean {
        val authors = authorsDataSource.findAllByAuthorFullName(author.fullName)
        return authors.isNotEmpty()
    }

    override fun checkThatAuthorExists(authorId: Long): Boolean {
        return authorsDataSource.existsById(authorId)
    }

    override fun checkThatQuoteExists(quote: CreateQuoteModel): Boolean {
        val authorExists = checkThatAuthorExists(quote.authorId)
        val tagExists = checkThatTagExists(quote.tagId)
        if (!authorExists || !tagExists) {
            return false
        }
        val quotes = quotesDataSource.getAllByText(quote.text)
        quotes.forEach { existingQuote ->
            if (existingQuote.isAs(quote)) {
                return true
            }
        }
        return false
    }

    override fun checkThatTagExists(tag: CreateTagModel): Boolean {
        val tags = tagsDataSource.getAllByTagName(tag.text)
        return tags.isNotEmpty()
    }

    override fun checkThatTagExists(tagId: Long): Boolean {
        return tagsDataSource.existsById(tagId)
    }

    override fun createNewAuthor(author: CreateAuthorModel): AuthorEntity {
        val entity = AuthorEntity(
            authorFullName = author.fullName,
        )
        return authorsDataSource.saveAndFlush(entity)
    }

    override fun createNewTag(tag: CreateTagModel): TagEntity {
        val entity = TagEntity(
            tagName = tag.text
        )
        return tagsDataSource.saveAndFlush(entity)
    }

    override fun createNewQuote(quote: CreateQuoteModel): QuoteEntity {
        val tagEntity = tagsDataSource.findById(quote.tagId).get()
        val authorEntity = authorsDataSource.findById(quote.authorId).get()
        val quoteEntity = QuoteEntity(
            text = quote.text,
            author = authorEntity,
            tag = tagEntity,
        )
        val resultEntity = quotesDataSource.saveAndFlush(quoteEntity)

        updateTag(tagEntity, resultEntity)
        updateAuthor(authorEntity, resultEntity)

        return resultEntity
    }

    private fun updateTag(tag: TagEntity, quote: QuoteEntity) {
        tag.quotes.add(quote)
        tagsDataSource.saveAndFlush(tag)
    }

    private fun updateAuthor(author: AuthorEntity, quote: QuoteEntity) {
        author.quotes.add(quote)
        authorsDataSource.saveAndFlush(author)
    }

    private fun QuoteEntity.isAs(quote: CreateQuoteModel): Boolean {
        if (author?.id != quote.authorId) {
            return false
        }
        if (tag?.id != quote.tagId) {
            return false
        }
        if (text.toLowerCase() != quote.text.toLowerCase()) {
            return false
        }
        return true
    }
}
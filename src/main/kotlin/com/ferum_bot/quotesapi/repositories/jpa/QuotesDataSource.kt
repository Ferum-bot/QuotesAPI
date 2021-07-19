package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface QuotesDataSource: JpaRepository<QuoteEntity, Long> {

    @Query("SELECT id FROM QuoteEntity")
    fun getAllAvailableIds(): List<Long>

    @Query("SELECT QuoteEntity FROM QuoteEntity WHERE author.authorFullName = ?1")
    fun getAllWhereAuthorIs(authorName: String): List<QuoteEntity>

    @Query("SELECT QuoteEntity FROM QuoteEntity  WHERE tag.tagName = ?1")
    fun getAllWhereTagIs(tag: String): List<QuoteEntity>
}
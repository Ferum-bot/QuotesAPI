package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface QuotesDataSource: JpaRepository<QuoteEntity, Long> {

    @Query(
        value = "SELECT id FROM quote_entity",
        nativeQuery = true
    )
    fun getAllAvailableIds(): List<Long>

    @Query(
        value = "SELECT ALL FROM QuoteEntity WHERE author.authorFullName = ?1",
        nativeQuery = true
    )
    fun getAllWhereAuthorIs(authorName: String): List<QuoteEntity>

    @Query(
        value = "SELECT ALL FROM QuoteEntity  WHERE tag.tagName = ?1",
        nativeQuery = true,
    )
    fun getAllWhereTagIs(tag: String): List<QuoteEntity>

    fun getAllByText(text: String): List<QuoteEntity>
}
package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface QuotesDataSource: JpaRepository<QuoteEntity, Long> {

    @Query(
        value = "SELECT id FROM quote_entity",
        nativeQuery = true
    )
    fun getAllAvailableIds(): List<Long>

    @Query(
        value = "SELECT * FROM quote_entity WHERE quote_entity.author_entity_id=ANY(SELECT author_entity.id FROM author_entity WHERE author_entity.author_full_name=:authorName) ORDER BY quote_entity.id" ,
        nativeQuery = true
    )
    fun getAllWhereAuthorIs(
        @Param("authorName")
        authorName: String
    ): List<QuoteEntity>

    @Query(
        value = "SELECT * FROM quote_entity WHERE quote_entity.tag_entity_id=ANY(SELECT tag_entity.id FROM tag_entity WHERE tag_entity.tag_name=:tag) ORDER BY quote_entity.id",
        nativeQuery = true,
    )
    fun getAllWhereTagIs(
        @Param("tag")
        tag: String
    ): List<QuoteEntity>

    fun getAllByText(text: String): List<QuoteEntity>
}
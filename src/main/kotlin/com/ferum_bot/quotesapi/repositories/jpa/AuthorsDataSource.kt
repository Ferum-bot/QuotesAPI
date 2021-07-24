package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AuthorsDataSource: JpaRepository<AuthorEntity, Long> {

    @Query(
        value = "SELECT * FROM author_entity WHERE author_full_name LIKE CONCAT('%', :text, '%') ORDER BY author_full_name",
        nativeQuery = true,
    )
    fun findAllWhereNameContains(
        @Param("text")
        text: String
    ): List<AuthorEntity>

    fun findAllByAuthorFullName(text: String): List<AuthorEntity>
}
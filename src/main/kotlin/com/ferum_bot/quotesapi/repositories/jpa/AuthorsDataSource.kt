package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthorsDataSource: JpaRepository<AuthorEntity, Long> {

    @Query(
        value = "SELECT ALL FROM author_entity WHERE author_full_name=?1",
        nativeQuery = true,
    )
    fun getAllAuthorsWhereNameContains(text: String, pageable: Pageable): Page<AuthorEntity>

}
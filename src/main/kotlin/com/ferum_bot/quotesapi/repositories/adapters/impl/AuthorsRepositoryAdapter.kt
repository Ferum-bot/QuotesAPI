package com.ferum_bot.quotesapi.repositories.adapters.impl

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.repositories.adapters.AuthorsDataSourceAdapter

class AuthorsRepositoryAdapter: AuthorsDataSourceAdapter{

    override fun adaptAuthor(author: AuthorEntity): AuthorDTO {
        val id = author.id!!
        val fullName = author.authorFullName
        val numberOfQuotes = author.quotes.size

        return AuthorDTO(
            id, fullName, numberOfQuotes
        )
    }

    override fun adaptAuthors(authors: Collection<AuthorEntity>): Collection<AuthorDTO> {
        return authors.map { adaptAuthor(it) }
    }

}
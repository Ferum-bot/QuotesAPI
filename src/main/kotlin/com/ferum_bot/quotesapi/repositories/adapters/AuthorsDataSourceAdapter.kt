package com.ferum_bot.quotesapi.repositories.adapters

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.models.entity.AuthorEntity

interface AuthorsDataSourceAdapter {

    fun adaptAuthor(author: AuthorEntity): AuthorDTO

    fun adaptAuthors(authors: Collection<AuthorEntity>): Collection<AuthorDTO>

}
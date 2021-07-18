package com.ferum_bot.quotesapi.repositories

import com.ferum_bot.quotesapi.models.dto.AuthorDTO

interface AuthorsRepository: BaseApiRepository {

    fun getAllAuthors(): Collection<AuthorDTO>

    fun getAuthorsFrom(start: Int, count: Int): Collection<AuthorDTO>
}
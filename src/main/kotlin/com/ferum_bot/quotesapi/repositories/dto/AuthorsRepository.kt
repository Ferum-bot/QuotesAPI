package com.ferum_bot.quotesapi.repositories.dto

import com.ferum_bot.quotesapi.models.dto.AuthorDTO

interface AuthorsRepository: BaseApiRepository {

    fun getAllAuthors(): Collection<AuthorDTO>

    fun getAuthorsFrom(page: Int, size: Int): Collection<AuthorDTO>

    fun searchAuthors(text: String, page: Int, size: Int): Collection<AuthorDTO>
}
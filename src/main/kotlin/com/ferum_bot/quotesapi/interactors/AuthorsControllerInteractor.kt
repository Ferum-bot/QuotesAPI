package com.ferum_bot.quotesapi.interactors

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse

interface AuthorsControllerInteractor {

    fun getAllAuthors(): QuotesResponse<Collection<AuthorDTO>>

    fun getAllAuthorsFrom(page: Int, size: Int): QuotesResponse<Collection<AuthorDTO>>

    fun searchAuthors(name: String, page: Int, size: Int): QuotesResponse<Collection<AuthorDTO>>
}
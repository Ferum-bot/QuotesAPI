package com.ferum_bot.quotesapi.interactors

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse

interface AuthorsControllerInteractor {

    fun getAllAuthors(): QuotesResponse<Collection<AuthorDTO>>

    fun getAllAuthorsFrom(startOffset: Int, count: Int): QuotesResponse<Collection<AuthorDTO>>
}
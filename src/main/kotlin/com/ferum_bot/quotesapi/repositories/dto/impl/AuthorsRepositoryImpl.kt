package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.repositories.adapters.AuthorsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.AuthorsRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import com.ferum_bot.quotesapi.util.extensions.getSubPage
import org.springframework.data.domain.PageRequest

class AuthorsRepositoryImpl(
    quotesDataSource: QuotesDataSource,
    tagsDataSource: TagsDataSource,
    authorsDataSource: AuthorsDataSource,

    private val adapter: AuthorsDataSourceAdapter,
):  BaseRepository(quotesDataSource, tagsDataSource, authorsDataSource),
    AuthorsRepository {

    override fun getAllAuthors(): Collection<AuthorDTO> {
        val rawAuthors = authorsDataSource.findAll()
        return adapter.adaptAuthors(rawAuthors)
    }

    override fun getAuthorsFrom(page: Int, size: Int): Collection<AuthorDTO> {
        if (size == 0) {
            return emptyList()
        }
        val pageRequest = PageRequest.of(page, size)
        val resultPage = authorsDataSource.findAll(pageRequest)
        val rawAuthors = resultPage.content
        return adapter.adaptAuthors(rawAuthors)
    }

    override fun searchAuthors(text: String, page: Int, size: Int): Collection<AuthorDTO> {
        if (size == 0) {
            return emptyList()
        }
        val rawAuthors = authorsDataSource.findAllWhereNameContains(text)
            .getSubPage(page, size)
        return adapter.adaptAuthors(rawAuthors)
    }
}
package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.dto.AuthorDTO
import com.ferum_bot.quotesapi.repositories.adapters.AuthorsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.AuthorsRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import com.ferum_bot.quotesapi.util.extensions.getSubPage
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class AuthorsRepositoryImpl(
    quotesDataSource: QuotesDataSource,
    tagsDataSource: TagsDataSource,
    authorsDataSource: AuthorsDataSource,

    private val adapter: AuthorsDataSourceAdapter,
):  BaseRepository(quotesDataSource, tagsDataSource, authorsDataSource),
    AuthorsRepository {

    override fun getAllAuthors(): Collection<AuthorDTO> {
        val sort = Sort.by("authorFullName")
        val rawAuthors = authorsDataSource.findAll(sort)
        return adapter.adaptAuthors(rawAuthors)
    }

    override fun getAuthorsFrom(page: Int, size: Int): Collection<AuthorDTO> {
        if (size == 0) {
            return emptyList()
        }
        val sort = Sort.by("authorFullName")
        val pageRequest = PageRequest.of(page, size).withSort(sort)
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
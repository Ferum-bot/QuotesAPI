package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.repositories.adapters.TagsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.TagsRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import org.springframework.data.domain.Sort

class TagsRepositoryImpl(
    quotesDataSource: QuotesDataSource,
    tagsDataSource: TagsDataSource,
    authorsDataSource: AuthorsDataSource,

    private val adapter: TagsDataSourceAdapter
):  BaseRepository(quotesDataSource, tagsDataSource, authorsDataSource),
    TagsRepository {

    override fun getAllTags(): Collection<TagDTO> {
        val sort = Sort.by("tagName")
        val rawTags = tagsDataSource.findAll(sort)
        return adapter.adaptTags(rawTags)
    }
}
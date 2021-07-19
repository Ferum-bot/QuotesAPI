package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.repositories.adapters.TagsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.TagsRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource

class TagsRepositoryImpl(
    quotesDataSource: QuotesDataSource,
    tagsDataSource: TagsDataSource,
    authorsDataSource: AuthorsDataSource,

    private val adapter: TagsDataSourceAdapter
):  BaseRepository(quotesDataSource, tagsDataSource, authorsDataSource),
    TagsRepository {

    override fun getAllTags(): Collection<TagDTO> {
        val rawTags = tagsDataSource.findAll()
        return adapter.adaptTags(rawTags)
    }
}
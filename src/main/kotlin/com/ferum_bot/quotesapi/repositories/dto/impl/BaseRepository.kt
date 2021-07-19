package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.repositories.dto.BaseApiRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource

abstract class BaseRepository(
    protected val quotesDataSource: QuotesDataSource,
    protected val tagsDataSource: TagsDataSource,
    protected val authorsDataSource: AuthorsDataSource,
): BaseApiRepository {

    override fun getNumberOfAvailableAuthors(): Int {
        return authorsDataSource.count().toInt()
    }

    override fun getNumberOfAvailableQuotes(): Int {
        return quotesDataSource.count().toInt()
    }

    override fun getNumberOfAvailableTags(): Int {
        return tagsDataSource.count().toInt()
    }
}
package com.ferum_bot.quotesapi.repositories.dto.impl

import com.ferum_bot.quotesapi.models.dto.QuoteDTO
import com.ferum_bot.quotesapi.repositories.adapters.QuotesDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.QuotesRepository
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import com.ferum_bot.quotesapi.util.RandomUtil
import org.springframework.data.domain.PageRequest

class QuotesRepositoryImpl(
    quotesDataSource: QuotesDataSource,
    tagsDataSource: TagsDataSource,
    authorsDataSource: AuthorsDataSource,

    private val adapter: QuotesDataSourceAdapter
):  BaseRepository(quotesDataSource, tagsDataSource, authorsDataSource),
    QuotesRepository {

    override fun getAllQuotes(): Collection<QuoteDTO> {
        val rawQuotes = quotesDataSource.findAll()
        return adapter.adaptQuotes(rawQuotes)
    }

    override fun getQuotesFrom(page: Int, size: Int): Collection<QuoteDTO> {
        val pageRequest = PageRequest.of(page, size)
        val resultPage = quotesDataSource.findAll(pageRequest)
        val rawQuotes = resultPage.content
        return adapter.adaptQuotes(rawQuotes)
    }

    override fun getQuotesWithAuthor(author: String): Collection<QuoteDTO> {
        val rawQuotes = quotesDataSource.getAllWhereAuthorIs(author)
        return adapter.adaptQuotes(rawQuotes)
    }

    override fun getQuotesWithTag(tag: String): Collection<QuoteDTO> {
        val rawQuotes = quotesDataSource.getAllWhereTagIs(tag)
        return adapter.adaptQuotes(rawQuotes)
    }

    override fun getRandomQuotes(count: Int): Collection<QuoteDTO> {
        val availableIds = quotesDataSource.getAllAvailableIds()
        val idsCount = availableIds.count()
        val randomPositions = RandomUtil.getRandomIntsBetween(
            start = 0, end = idsCount - 1, count,
        )
        val randomIds = List(randomPositions.size) { pos ->
            val idsPos = randomPositions[pos]
            return@List availableIds[idsPos]
        }
        val rawQuotes = quotesDataSource.findAllById(randomIds)
        return adapter.adaptQuotes(rawQuotes)
    }
}
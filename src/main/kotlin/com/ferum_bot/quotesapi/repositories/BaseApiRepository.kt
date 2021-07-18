package com.ferum_bot.quotesapi.repositories

interface BaseApiRepository {

    fun getNumberOfAvailableQuotes(): Int

    fun getNumberOfAvailableTags(): Int

    fun getNumberOfAvailableAuthors(): Int

}
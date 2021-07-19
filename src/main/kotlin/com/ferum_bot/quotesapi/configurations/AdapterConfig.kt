package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.repositories.adapters.AuthorsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.adapters.QuotesDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.adapters.TagsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.adapters.impl.AuthorsRepositoryAdapter
import com.ferum_bot.quotesapi.repositories.adapters.impl.QuotesRepositoryAdapter
import com.ferum_bot.quotesapi.repositories.adapters.impl.TagsRepositoryAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AdapterConfig {

    @Bean
    fun provideAuthorRepositoryAdapter(): AuthorsDataSourceAdapter {
        return AuthorsRepositoryAdapter()
    }

    @Bean
    fun provideTagsRepositoryAdapter(): TagsDataSourceAdapter {
        return TagsRepositoryAdapter()
    }

    @Bean
    fun provideQuotesRepositoryAdapter(): QuotesDataSourceAdapter {
        return QuotesRepositoryAdapter()
    }
}
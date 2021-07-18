package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.repositories.AuthorsRepository
import com.ferum_bot.quotesapi.repositories.QuotesRepository
import com.ferum_bot.quotesapi.repositories.TagsRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfig {

    @Bean
    fun provideQuotesRepository(): QuotesRepository {

    }

    @Bean
    fun provideTagsRepository(): TagsRepository {

    }

    @Bean
    fun provideAuthorsRepository(): AuthorsRepository {

    }
}
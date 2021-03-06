package com.ferum_bot.quotesapi.application

import com.ferum_bot.quotesapi.configurations.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import com.ferum_bot.quotesapi.models.entity.QuoteEntity
import com.ferum_bot.quotesapi.models.entity.AuthorEntity
import com.ferum_bot.quotesapi.models.entity.TagEntity
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import org.springframework.boot.SpringApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    ScanConfig::class,
    InteractorsConfig::class,
    HandlersConfig::class,
    RepositoryConfig::class,
    AdapterConfig::class,
    HerokuConfig::class,
    LoggingConfig::class,
)
@EntityScan(basePackageClasses = [
    QuoteEntity::class,
    AuthorEntity::class,
    TagEntity::class
])
@EnableJpaRepositories(basePackageClasses = [
    QuotesDataSource::class,
    AuthorsDataSource::class,
    TagsDataSource::class
])
class QuotesApiApplication


fun main(args: Array<String>) {
    runApplication<QuotesApiApplication>(*args)
}
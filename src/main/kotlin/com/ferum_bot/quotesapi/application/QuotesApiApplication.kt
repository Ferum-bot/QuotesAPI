package com.ferum_bot.quotesapi.application

import com.ferum_bot.quotesapi.configurations.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    ScanConfig::class,
    InteractorsConfig::class,
    HandlersConfig::class,
    RepositoryConfig::class,
    AdapterConfig::class,
)
class QuotesApiApplication

fun main(args: Array<String>) {
    runApplication<QuotesApiApplication>(*args)
}

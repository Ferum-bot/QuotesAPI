package com.ferum_bot.quotesapi.application

import com.ferum_bot.quotesapi.configurations.HandlersConfig
import com.ferum_bot.quotesapi.configurations.InteractorsConfig
import com.ferum_bot.quotesapi.configurations.RepositoryConfig
import com.ferum_bot.quotesapi.configurations.ScanConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(
    ScanConfig::class,
    InteractorsConfig::class,
    HandlersConfig::class,
    RepositoryConfig::class,
)
class QuotesApiApplication

fun main(args: Array<String>) {
    runApplication<QuotesApiApplication>(*args)
}

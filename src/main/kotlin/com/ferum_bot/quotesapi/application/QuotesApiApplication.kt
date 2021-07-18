package com.ferum_bot.quotesapi.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuotesApiApplication

fun main(args: Array<String>) {
    runApplication<QuotesApiApplication>(*args)
}

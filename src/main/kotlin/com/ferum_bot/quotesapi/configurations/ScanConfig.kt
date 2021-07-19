package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.controllers.QuoteAuthorsController
import com.ferum_bot.quotesapi.controllers.QuotesController
import com.ferum_bot.quotesapi.controllers.TagsController
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [
    QuoteAuthorsController::class,
    QuotesController::class,
    TagsController::class,
])
class ScanConfig
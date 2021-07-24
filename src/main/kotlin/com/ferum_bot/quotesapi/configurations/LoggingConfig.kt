package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.util.logging.ApiLogger
import com.ferum_bot.quotesapi.util.logging.DefaultApiLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoggingConfig {

    @Bean
    fun provideApiLogger(): ApiLogger {
        return DefaultApiLogger()
    }

}
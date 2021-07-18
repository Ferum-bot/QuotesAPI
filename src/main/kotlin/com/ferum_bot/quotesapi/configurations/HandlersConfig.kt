package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.business.impl.EmptyPlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.errors.impl.BaseErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.handlers.security.impl.BaseSecurityHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HandlersConfig {

    @Bean
    fun provideErrorHandler(): ErrorHandler {
        return BaseErrorHandler()
    }

    @Bean
    fun providePlatformHandler(): PlatformHandler {
        return EmptyPlatformHandler()
    }

    @Bean
    fun provideSecurityHandler(): SecurityHandler {
        return BaseSecurityHandler()
    }
}
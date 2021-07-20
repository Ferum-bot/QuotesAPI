package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.handlers.errors.CreateEntityErrorHandler
import com.ferum_bot.quotesapi.interactors.AdminControllerInteractor
import com.ferum_bot.quotesapi.interactors.AuthorsControllerInteractor
import com.ferum_bot.quotesapi.interactors.QuotesControllerInteractor
import com.ferum_bot.quotesapi.interactors.TagsControllerInteractor
import com.ferum_bot.quotesapi.interactors.impl.AdminControllerInteractorImpl
import com.ferum_bot.quotesapi.interactors.impl.AuthorsControllerInteractorImpl
import com.ferum_bot.quotesapi.interactors.impl.QuotesControllerInteractorImpl
import com.ferum_bot.quotesapi.interactors.impl.TagsControllerInteractorImpl
import com.ferum_bot.quotesapi.repositories.dto.AdminRepository
import com.ferum_bot.quotesapi.repositories.dto.AuthorsRepository
import com.ferum_bot.quotesapi.repositories.dto.QuotesRepository
import com.ferum_bot.quotesapi.repositories.dto.TagsRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InteractorsConfig {

    @Bean
    fun provideAuthorsControllerInteractor(
        repository: AuthorsRepository
    ): AuthorsControllerInteractor {
        return AuthorsControllerInteractorImpl(repository)
    }

    @Bean
    fun provideTagsControllerInteractor(
        repository: TagsRepository,
    ): TagsControllerInteractor {
        return TagsControllerInteractorImpl(repository)
    }

    @Bean
    fun provideQuotesControllerInteractor(
        repository: QuotesRepository
    ): QuotesControllerInteractor {
        return QuotesControllerInteractorImpl(repository)
    }

    @Bean
    fun provideAdminControllerInteractor(
        repository: AdminRepository,
        errorHandler: CreateEntityErrorHandler,
    ): AdminControllerInteractor {
        return AdminControllerInteractorImpl(repository, errorHandler)
    }
}
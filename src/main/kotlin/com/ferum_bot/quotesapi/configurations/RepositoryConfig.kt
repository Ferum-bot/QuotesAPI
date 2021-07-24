package com.ferum_bot.quotesapi.configurations

import com.ferum_bot.quotesapi.repositories.adapters.AuthorsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.adapters.QuotesDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.adapters.TagsDataSourceAdapter
import com.ferum_bot.quotesapi.repositories.dto.AdminRepository
import com.ferum_bot.quotesapi.repositories.dto.AuthorsRepository
import com.ferum_bot.quotesapi.repositories.dto.QuotesRepository
import com.ferum_bot.quotesapi.repositories.dto.TagsRepository
import com.ferum_bot.quotesapi.repositories.dto.impl.AuthorsRepositoryImpl
import com.ferum_bot.quotesapi.repositories.dto.impl.DefaultAdminRepository
import com.ferum_bot.quotesapi.repositories.dto.impl.QuotesRepositoryImpl
import com.ferum_bot.quotesapi.repositories.dto.impl.TagsRepositoryImpl
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jca.support.LocalConnectionFactoryBean
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class RepositoryConfig {

    @Autowired
    private lateinit var quotesDataSource: QuotesDataSource

    @Autowired
    private lateinit var authorsDataSource: AuthorsDataSource

    @Autowired
    private lateinit var tagsDataSource: TagsDataSource

    @Bean
    fun provideQuotesRepository(adapter: QuotesDataSourceAdapter): QuotesRepository {
        return QuotesRepositoryImpl(
            quotesDataSource, tagsDataSource, authorsDataSource, adapter
        )
    }

    @Bean
    fun provideTagsRepository(adapter: TagsDataSourceAdapter): TagsRepository {
        return TagsRepositoryImpl(
            quotesDataSource, tagsDataSource, authorsDataSource, adapter
        )
    }

    @Bean
    fun provideAuthorsRepository(adapter: AuthorsDataSourceAdapter): AuthorsRepository {
        return AuthorsRepositoryImpl(
            quotesDataSource, tagsDataSource, authorsDataSource, adapter
        )
    }

    @Bean
    fun provideAdminRepository(): AdminRepository {
        return DefaultAdminRepository(
            quotesDataSource, tagsDataSource, authorsDataSource
        )
    }
}
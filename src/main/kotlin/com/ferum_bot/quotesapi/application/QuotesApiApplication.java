package com.ferum_bot.quotesapi.application;

import com.ferum_bot.quotesapi.configurations.*;
import com.ferum_bot.quotesapi.models.entity.AuthorEntity;
import com.ferum_bot.quotesapi.models.entity.QuoteEntity;
import com.ferum_bot.quotesapi.models.entity.TagEntity;
import com.ferum_bot.quotesapi.repositories.jpa.AuthorsDataSource;
import com.ferum_bot.quotesapi.repositories.jpa.QuotesDataSource;
import com.ferum_bot.quotesapi.repositories.jpa.TagsDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({
        ScanConfig.class,
        InteractorsConfig.class,
        HandlersConfig.class,
        RepositoryConfig.class,
        AdapterConfig.class,
        HerokuConfig.class
})
@EntityScan(basePackageClasses = {
        QuoteEntity.class,
        AuthorEntity.class,
        TagEntity.class
})
@EnableJpaRepositories(basePackageClasses = {
        QuotesDataSource.class,
        AuthorsDataSource.class,
        TagsDataSource.class
})
public class QuotesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApiApplication.class, args);
    }

}
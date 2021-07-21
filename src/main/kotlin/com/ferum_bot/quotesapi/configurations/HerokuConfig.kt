package com.ferum_bot.quotesapi.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import java.sql.Connection
import java.sql.DriverManager

@Configuration
class HerokuConfig {

//    @Bean
//    fun provideDataSource(): Connection {
//        val dbUri = URI(System.getenv("DATABASE_URL"))
//
//        val userName = dbUri.userInfo.split(":")[0]
//        val password = dbUri.userInfo.split(":")[1]
//        val url = "jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}?sslmode=require"
//
//        return DriverManager.getConnection(url, userName, password)
//    }

}
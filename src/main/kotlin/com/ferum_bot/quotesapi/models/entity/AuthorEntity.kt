package com.ferum_bot.quotesapi.models.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class AuthorEntity(

    @Id
    val id: Long,

    val authorFullName: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)

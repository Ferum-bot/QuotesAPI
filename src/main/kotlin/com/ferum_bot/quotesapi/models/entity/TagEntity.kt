package com.ferum_bot.quotesapi.models.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TagEntity(

    @Id
    val id: Long,

    val tagName: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,
)

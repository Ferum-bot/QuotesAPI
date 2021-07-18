package com.ferum_bot.quotesapi.models.entity

import java.time.LocalDateTime
import javax.annotation.processing.Generated
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class QuoteEntity(

    @Generated
    @Id
    val id: Long,

    val text: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,

) {

}
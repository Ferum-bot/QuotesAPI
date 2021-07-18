package com.ferum_bot.quotesapi.models.dto

data class QuoteDTO(

    val id: Long,

    val text: String,

    val authorFullName: String,

    val tag: String,
)

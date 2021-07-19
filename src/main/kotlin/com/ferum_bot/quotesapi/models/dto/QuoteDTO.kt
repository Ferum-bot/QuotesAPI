package com.ferum_bot.quotesapi.models.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class QuoteDTO(

    val id: Long,

    val text: String,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val authorFullName: String?,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val tag: String?,
)

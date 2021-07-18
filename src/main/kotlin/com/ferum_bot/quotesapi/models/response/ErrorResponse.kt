package com.ferum_bot.quotesapi.models.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(

    val statusCode: Int,

    val errorMessage: String,

    val errors: Collection<String>?
)

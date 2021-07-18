package com.ferum_bot.quotesapi.models.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(

    val statusCode: Int,

    val errorMessage: String,

    @JsonInclude(JsonInclude.Include.ALWAYS)
    val errors: Collection<String>
) {

    companion object {

        fun getFrom(code: Int, text: String): ErrorResponse
        = ErrorResponse(
            statusCode = code,
            errorMessage = text,
            errors = emptyList()
        )
    }

}

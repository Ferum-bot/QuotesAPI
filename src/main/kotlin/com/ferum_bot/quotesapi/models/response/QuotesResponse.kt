package com.ferum_bot.quotesapi.models.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude

data class QuotesResponse<R>(

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val error: ErrorResponse?,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: R?,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val numberOfQuotes: Int? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val numberOfTags: Int? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val numberOfAuthors: Int? = null,
)

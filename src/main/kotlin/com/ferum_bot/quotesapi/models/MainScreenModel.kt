package com.ferum_bot.quotesapi.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.ferum_bot.quotesapi.models.dto.QuoteDTO

data class MainScreenModel(

    @JsonProperty(value = "authorsCount")
    val numberOfAvailableAuthors: Int,

    @JsonProperty(value = "tagsCount")
    val numberOfAvailableTags: Int,

    @JsonProperty(value = "randomQuotes")
    val randomQuotes: Collection<QuoteDTO>
)

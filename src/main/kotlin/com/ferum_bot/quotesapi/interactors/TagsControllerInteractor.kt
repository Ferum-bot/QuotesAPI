package com.ferum_bot.quotesapi.interactors

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.models.response.QuotesResponse

interface TagsControllerInteractor {

    fun getAllTags(): QuotesResponse<Collection<TagDTO>>

}
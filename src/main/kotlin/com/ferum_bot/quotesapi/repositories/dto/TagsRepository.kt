package com.ferum_bot.quotesapi.repositories.dto

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.repositories.dto.BaseApiRepository

interface TagsRepository: BaseApiRepository {

    fun getAllTags(): Collection<TagDTO>

}
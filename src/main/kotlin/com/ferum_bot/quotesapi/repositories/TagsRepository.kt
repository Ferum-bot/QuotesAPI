package com.ferum_bot.quotesapi.repositories

import com.ferum_bot.quotesapi.models.dto.TagDTO

interface TagsRepository: BaseApiRepository {

    fun getAllTags(): Collection<TagDTO>

}
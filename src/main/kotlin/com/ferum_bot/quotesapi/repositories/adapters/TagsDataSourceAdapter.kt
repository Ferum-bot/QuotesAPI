package com.ferum_bot.quotesapi.repositories.adapters

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.models.entity.TagEntity

interface TagsDataSourceAdapter {

    fun adaptTag(tag: TagEntity): TagDTO

    fun adaptTags(tags: Collection<TagEntity>): Collection<TagDTO>

}
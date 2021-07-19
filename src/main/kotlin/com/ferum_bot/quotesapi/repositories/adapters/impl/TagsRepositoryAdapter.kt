package com.ferum_bot.quotesapi.repositories.adapters.impl

import com.ferum_bot.quotesapi.models.dto.TagDTO
import com.ferum_bot.quotesapi.models.entity.TagEntity
import com.ferum_bot.quotesapi.repositories.adapters.TagsDataSourceAdapter

class TagsRepositoryAdapter: TagsDataSourceAdapter {

    override fun adaptTag(tag: TagEntity): TagDTO {
        val id = tag.id!!
        val name = tag.tagName
        val numberOfQuotes = tag.quotes.size

        return TagDTO(
            id, name, numberOfQuotes
        )
    }

    override fun adaptTags(tags: Collection<TagEntity>): Collection<TagDTO> {
        return tags.map { adaptTag(it) }
    }

}
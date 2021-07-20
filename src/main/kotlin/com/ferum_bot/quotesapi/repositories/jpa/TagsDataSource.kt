package com.ferum_bot.quotesapi.repositories.jpa

import com.ferum_bot.quotesapi.models.entity.TagEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TagsDataSource: JpaRepository<TagEntity, Long> {

    fun getAllByTagName(tag: String): List<TagEntity>

}
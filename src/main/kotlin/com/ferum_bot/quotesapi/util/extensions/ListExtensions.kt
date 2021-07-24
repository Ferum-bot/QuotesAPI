package com.ferum_bot.quotesapi.util.extensions

internal fun <T: Any> List<T>.getSubPage(page: Int, size: Int): List<T> {
    if (size <= 0) {
        return emptyList()
    }
    if (page <= 0) {
        return emptyList()
    }

    val startIndex = page * size - size
    val endIndex = page * size

    return subList(startIndex, endIndex).toList()
}
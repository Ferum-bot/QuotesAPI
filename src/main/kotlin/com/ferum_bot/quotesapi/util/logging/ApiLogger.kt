package com.ferum_bot.quotesapi.util.logging

import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.springframework.http.ResponseEntity

interface ApiLogger {

    fun <T: Any> logAll(
        response: QuotesResponse<T>,
        extraMessage: () -> String,
    )

    fun <T: Any> logError(
        response: QuotesResponse<T>,
        extraMessage: () -> String,
    )

    fun <T: Any> logSuccess(
        response: QuotesResponse<T>,
        extraMessage: () -> String,
    )

    fun logInfo(getMessage: () -> String)

    fun logDebug(getMessage: () -> String)

    fun logWarn(getMessage: () -> String)

    fun logError(getMessage: () -> String)

    fun logInvalidAdminKey(invalidKey: String)

    fun logInvalidAccessKey(invalidKey: String)
}
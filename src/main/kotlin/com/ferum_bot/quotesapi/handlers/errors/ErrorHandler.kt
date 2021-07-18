package com.ferum_bot.quotesapi.handlers.errors

import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors

/**
 * Handles the http errors and returns response entities.
 */
interface ErrorHandler {

    fun handleException(ex: Exception? = null): ResponseEntity<QuotesResponse<Nothing>>

    fun handleErrors(errors: Errors? = null): ResponseEntity<QuotesResponse<Nothing>>

    fun handleInvalidSecretKey(): ResponseEntity<QuotesResponse<Nothing>>

    fun handleBadRequest(ex: Exception? = null): ResponseEntity<QuotesResponse<Nothing>>

    fun handleBadRequest(getMessage: () -> String): ResponseEntity<QuotesResponse<Nothing>>

}
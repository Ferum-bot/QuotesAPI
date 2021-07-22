package com.ferum_bot.quotesapi.handlers.errors.impl

import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.models.response.ErrorResponse
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors

class BaseErrorHandler: ErrorHandler {

    override fun handleException(ex: Exception?): ResponseEntity<QuotesResponse<Nothing>> {
        val errorText = ex?.localizedMessage ?: "Something went wrong!"
        val errorResponse = ErrorResponse.getFrom(403, errorText)
        val response = QuotesResponse(data = null, error = errorResponse)
        return ResponseEntity(response, HttpStatus.FORBIDDEN)
    }

    override fun handleErrors(errors: Errors?): ResponseEntity<QuotesResponse<Nothing>> {
        if (errors == null) {
            return handleException(null)
        }
        if (!errors.hasErrors()) {
            return handleException(null)
        }
        val text = "Something went wrong!"
        val errorResponse = ErrorResponse(
            statusCode = 403,
            errorMessage = text,
            errors = errors.allErrors.map { it.toString() }
        )
        val response = QuotesResponse(data = null, error = errorResponse)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    override fun handleInvalidSecretKey(): ResponseEntity<QuotesResponse<Nothing>> {
        val errorText = "Server is not available now!"
        val errorResponse = ErrorResponse.getFrom(500, errorText)
        val response = QuotesResponse(data = null, error = errorResponse)
        return ResponseEntity.internalServerError().body(response)
    }

    override fun handleBadRequest(ex: Exception?): ResponseEntity<QuotesResponse<Nothing>> {
        return handleException(ex)
    }

    override fun handleBadRequest(getMessage: () -> String): ResponseEntity<QuotesResponse<Nothing>> {
        val errorText = getMessage.invoke()
        val errorResponse = ErrorResponse.getFrom(400, errorText)
        val response = QuotesResponse(data = null, error = errorResponse)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    override fun handleInvalidAdminKey(): ResponseEntity<QuotesResponse<Nothing>> {
        val errorText = "Invalid Admin key"
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.FORBIDDEN.value(),
            errorMessage = errorText,
            errors = emptyList()
        )
        val response = QuotesResponse(
            data = null,
            error = errorResponse
        )
        return ResponseEntity(response, HttpStatus.FORBIDDEN)
    }
}
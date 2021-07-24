package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.AuthorsControllerInteractor
import com.ferum_bot.quotesapi.util.PathConstants
import com.ferum_bot.quotesapi.util.logging.ApiLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("${PathConstants.BASE_PATH}${PathConstants.AUTHORS_BASE_PATH}")
class QuoteAuthorsController {

    @Autowired
    private lateinit var errorHandler: ErrorHandler

    @Autowired
    private lateinit var securityHandler: SecurityHandler

    @Autowired
    private lateinit var platformHandler: PlatformHandler

    @Autowired
    private lateinit var interactor: AuthorsControllerInteractor

    @Autowired
    private lateinit var logger: ApiLogger

    @GetMapping("/all")
    fun getAllAuthors(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,
    ): ResponseEntity<*> {
        logger.logInfo { "Get all authors called" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getAllAuthors()
        logger.logError(response) { "Failed to get all authors" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/from")
    fun getAuthorsFrom(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "page")
        page: Int,

        @RequestParam(value = "size")
        size: Int,
    ): ResponseEntity<*> {
        logger.logInfo { "Get authors from called with page: $page and size: $size" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        if (page < 0) {
            logger.logWarn { "Get authors from called with invalid page: $page" }
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (size < 0) {
            logger.logWarn { "Get authors from called with invalid size: $size" }
            return errorHandler.handleBadRequest { "Count can't be under zero." }
        }

        val response = interactor.getAllAuthorsFrom(page, size)
        logger.logError(response) { "Failed get authors from call" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/searchFrom")
    fun searchAuthorsFrom(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "text")
        text: String,

        @RequestParam(value = "page")
        page: Int,

        @RequestParam(value = "size")
        size: Int,
    ): ResponseEntity<*> {
        logger.logInfo { "Search authors from called with text: $text, page: $page, size: $size" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        if (text.isBlank()) {
            logger.logWarn { "Search authors called with invalid text: $text" }
            return errorHandler.handleBadRequest { "Search text can't be empty." }
        }
        if (page < 0) {
            logger.logWarn { "Search authors called with invalid page: $page" }
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (size < 0) {
            logger.logWarn { "Search authors called with invalid size: $size" }
            return errorHandler.handleBadRequest { "Count can't be under zero." }
        }

        val response = interactor.searchAuthors(text, page, size)
        logger.logError(response) { "Failed to search authors from" }
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<*> {
        logger.logError { "Received error: $exception" }
        return errorHandler.handleException(exception)
    }
}
package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.QuotesControllerInteractor
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.util.PathConstants
import com.ferum_bot.quotesapi.util.logging.ApiLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.math.log

@RestController
@RequestMapping("${PathConstants.BASE_PATH}${PathConstants.QUOTES_BASE_PATH}")
class QuotesController {

    @Autowired
    private lateinit var errorHandler: ErrorHandler

    @Autowired
    private lateinit var securityHandler: SecurityHandler

    @Autowired
    private lateinit var platformHandler: PlatformHandler

    @Autowired
    private lateinit var interactor: QuotesControllerInteractor

    @Autowired
    private lateinit var logger: ApiLogger

    @GetMapping("/mainScreenModel")
    fun getMainScreenModel(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "quotesCount")
        quotesCount: Int,
    ): ResponseEntity<*> {
        logger.logInfo { "Get main screen model called with count: $quotesCount" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        if (quotesCount < 0) {
            logger.logWarn { "Get main screen called with invalid count: $quotesCount" }
            return errorHandler.handleBadRequest { "Quotes count can't be under zero." }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getMainScreenModel(quotesCount)
        logger.logError(response) { "Failed get main screen model" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/random")
    fun getRandomQuotes(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "count")
        quotesCount: Int,
    ): ResponseEntity<*> {
        logger.logInfo { "Get random quotes called with count: $quotesCount" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        if (quotesCount < 0) {
            logger.logWarn { "Get random quotes called with invalid count: $quotesCount" }
            return errorHandler.handleBadRequest { "Quotes count can't be under zero" }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getRandomQuotes(quotesCount)
        logger.logError(response) { "Failed to ger random quotes" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/all")
    fun getAllQuotes(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,
    ): ResponseEntity<*> {
        logger.logInfo { "Get all quotes called" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getAllQuotes()
        logger.logError(response) { "Failed to get all quotes" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/from")
    fun getQuotesFrom(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "page")
        page: Int,

        @RequestParam(value = "size")
        size: Int,
    ): ResponseEntity<*> {
        logger.logInfo { "Get quotes called from with page: $page and size: $size" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        if (page < 0) {
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (size < 0) {
            return errorHandler.handleBadRequest { "Quotes count can't be under zero." }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesFrom(page, size)
        logger.logError(response) { "Failed to get quotes from" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/withTag")
    fun getAllQuotesWithTag(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "tag")
        tag: String,
    ): ResponseEntity<*> {
        logger.logInfo { "Get quotes with tag called: $tag" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesWithTag(tag)
        logger.logError(response) { "Failed to get quotes with tag: $tag" }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/withAuthor")
    fun getAllQuotesWithAuthor(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "author")
        author: String,
    ): ResponseEntity<*> {
        logger.logInfo { "Get all quotes with author called: $author" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesFromAuthor(author)
        logger.logError(response) { "Failed to get all quotes with author: $author" }
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<*> {
        logger.logError { "Received error: $exception" }
        return errorHandler.handleException(exception)
    }
}
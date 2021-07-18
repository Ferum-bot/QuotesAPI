package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.QuotesControllerInteractor
import com.ferum_bot.quotesapi.models.MainScreenModel
import com.ferum_bot.quotesapi.models.response.QuotesResponse
import com.ferum_bot.quotesapi.util.PathConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/mainScreenModel")
    fun getMainScreenModel(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "quotesCount")
        quotesCount: Int,
    ): ResponseEntity<*> {
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        if (quotesCount < 0) {
            return errorHandler.handleBadRequest { "Quotes count can't be under zero." }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getMainScreenModel(quotesCount)
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
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        if (quotesCount < 0) {
            return errorHandler.handleBadRequest { "Quotes count can't be under zero" }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getRandomQuotes(quotesCount)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/all")
    fun getAllQuotes(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,
    ): ResponseEntity<*> {
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getAllQuotes()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/from")
    fun getQuotesFrom(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,

        @RequestParam(value = "startOffset")
        startOffset: Int,

        @RequestParam(value = "count")
        count: Int,
    ): ResponseEntity<*> {
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        if (startOffset < 0) {
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (count < 0) {
            return errorHandler.handleBadRequest { "Quotes count can't be under zero." }
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesFrom(startOffset, count)
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
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesWithTag(tag)
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
        val keyIsValid = securityHandler.checkTheSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getQuotesFromAuthor(author)
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<QuotesResponse<Nothing>> {
        return errorHandler.handleException(exception)
    }
}
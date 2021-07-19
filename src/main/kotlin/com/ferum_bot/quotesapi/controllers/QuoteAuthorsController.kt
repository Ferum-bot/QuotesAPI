package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.AuthorsControllerInteractor
import com.ferum_bot.quotesapi.util.PathConstants
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

    @GetMapping("/all")
    fun getAllAuthors(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,
    ): ResponseEntity<*> {
        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getAllAuthors()
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
        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        if (page < 0) {
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (size < 0) {
            return errorHandler.handleBadRequest { "Count can't be under zero." }
        }

        val response = interactor.getAllAuthorsFrom(page, size)
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
        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            return errorHandler.handleInvalidSecretKey()
        }
        if (text.isBlank()) {
            return errorHandler.handleBadRequest { "Search text can't be empty." }
        }
        if (page < 0) {
            return errorHandler.handleBadRequest { "Offset can't be under zero." }
        }
        if (size < 0) {
            return errorHandler.handleBadRequest { "Count can't be under zero." }
        }

        val response = interactor.searchAuthors(text, page, size)
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<*> {
        return errorHandler.handleException(exception)
    }
}
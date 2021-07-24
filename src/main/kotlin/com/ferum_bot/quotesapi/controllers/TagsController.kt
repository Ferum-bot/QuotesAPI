package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.TagsControllerInteractor
import com.ferum_bot.quotesapi.util.PathConstants
import com.ferum_bot.quotesapi.util.logging.ApiLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.math.log

@RestController
@RequestMapping("${PathConstants.BASE_PATH}${PathConstants.TAGS_BASE_PATH}")
class TagsController {

    @Autowired
    private lateinit var errorHandler: ErrorHandler

    @Autowired
    private lateinit var platformHandler: PlatformHandler

    @Autowired
    private lateinit var securityHandler: SecurityHandler

    @Autowired
    private lateinit var interactor: TagsControllerInteractor

    @Autowired
    private lateinit var logger: ApiLogger

    @GetMapping("/all")
    fun getAllTags(
        @RequestHeader(value = "Secret-Key", required = true)
        secretKey: String,

        @RequestHeader(value = "Platform", required = false, defaultValue = "Undefined")
        platform: String,
    ): ResponseEntity<*> {
        logger.logInfo { "Get all tags called" }

        val keyIsValid = securityHandler.checkTheGetSecretKey(secretKey)
        if (!keyIsValid) {
            logger.logInvalidAccessKey(secretKey)
            return errorHandler.handleInvalidSecretKey()
        }
        platformHandler.processThePlatform(platform)

        val response = interactor.getAllTags()
        logger.logError(response) { "Failed get all tags" }
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<*> {
        logger.logError { "Received error: $exception" }
        return errorHandler.handleException(exception)
    }
}
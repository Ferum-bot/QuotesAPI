package com.ferum_bot.quotesapi.controllers

import com.ferum_bot.quotesapi.handlers.business.PlatformHandler
import com.ferum_bot.quotesapi.handlers.errors.ErrorHandler
import com.ferum_bot.quotesapi.handlers.security.SecurityHandler
import com.ferum_bot.quotesapi.interactors.TagsControllerInteractor
import com.ferum_bot.quotesapi.util.PathConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/all")
    fun getAllTags(
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

        val response = interactor.getAllTags()
        return ResponseEntity.ok(response)
    }

    @ExceptionHandler
    fun errorHandler(exception: Exception): ResponseEntity<*> {
        return errorHandler.handleException(exception)
    }
}